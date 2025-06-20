package br.com.project.labtrack.service.impl;

import br.com.project.labtrack.domain.MonitoramentoTransporte;
import br.com.project.labtrack.dto.MonitoramentoTransporteDTO;
import br.com.project.labtrack.infra.cloudinary.CloudinaryService;
import br.com.project.labtrack.infra.exceptions.GenerationFailedException;
import br.com.project.labtrack.infra.exceptions.ObjectNotFound;
import br.com.project.labtrack.infra.qrcode.QRCodeGenerator;
import br.com.project.labtrack.utils.Mapper;
import br.com.project.labtrack.utils.StatusTransporte;
import br.com.project.labtrack.infra.utils.UsuarioAutenticado;
import br.com.project.labtrack.repository.InventarioItemRepository;
import br.com.project.labtrack.repository.MonitoramentoTransporteRepository;
import br.com.project.labtrack.service.MonitoramentoTransporteService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class MonitoramentoTransporteServiceImpl implements MonitoramentoTransporteService {

    @Autowired
    private MonitoramentoTransporteRepository monitoramentoTransporteRepository;

    @Autowired
    private InventarioItemRepository inventarioItemRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Transactional
    @Override
    public ResponseEntity<Void> addTransporte(MonitoramentoTransporteDTO transporteDTO) {

        var user = UsuarioAutenticado.pegarUsuarioAutenticado();

        var transporte = MonitoramentoTransporte.builder()
                .usuarioEnviado(user)
                .dataSaida(LocalDateTime.now())
                .statusTransporte(StatusTransporte.ENVIADO)
                .itens(transporteDTO.getItens().stream()
                        .map(x -> inventarioItemRepository.findByCodigoItemAndUsuarioId(x.getCodigoItem(), user.getId())
                                .orElseThrow(() -> new ObjectNotFound("Item não encontrado"))).toList())
                .build();

        monitoramentoTransporteRepository.save(transporte);

        try{
            monitoramentoTransporteRepository.updateQrCodeImageUrlTransporte(transporte.getCodigoTransporte(),
                    cloudinaryService.uploadQRCode(QRCodeGenerator.generate("Em manutenção...", 300, 300), transporte.getCodigoTransporte().toString()));
        } catch (Exception e){
            throw new GenerationFailedException("Erro ao gerar QR Code: " + e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Transactional
    @Override
    public ResponseEntity<Void> atualizarStatus(UUID codigoTransporte, StatusTransporte statusTransporte) {

        monitoramentoTransporteRepository.updateStatusTransporte(codigoTransporte, statusTransporte);

        return ResponseEntity.noContent().build();
    }

    @Transactional
    @Override
    public ResponseEntity<Void> atualizarUsuarioRecebido(UUID codigoTransporte) {
        var user = UsuarioAutenticado.pegarUsuarioAutenticado();

        monitoramentoTransporteRepository.updateUsuarioRecebido(codigoTransporte, user);
        monitoramentoTransporteRepository.updateStatusTransporte(codigoTransporte, StatusTransporte.RECEBIDO);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<MonitoramentoTransporteDTO> buscarTransportePorId(UUID codigoTransporte) {
        var transporte = monitoramentoTransporteRepository.findById(codigoTransporte)
                .orElseThrow(() -> new ObjectNotFound("Transporte não encontrado"));

        return ResponseEntity.ok(Mapper.parseTo(transporte, MonitoramentoTransporteDTO.class));
    }

    @Override
    public ResponseEntity<List<MonitoramentoTransporteDTO>> buscarTodosTransportesPorUsuario() {
        var user = UsuarioAutenticado.pegarUsuarioAutenticado();

        var dtos = Mapper.parseListTo(monitoramentoTransporteRepository
                .findAllTransporteByUsuarioId(user.getId()), MonitoramentoTransporteDTO.class);

        return ResponseEntity.ok(dtos);
    }

    @Override
    public ResponseEntity<MonitoramentoTransporteDTO> buscarTransportePorIdBOT(UUID codigoTransporte) {
        var transporte = monitoramentoTransporteRepository.findById(codigoTransporte)
                .orElseThrow(() -> new ObjectNotFound("Transporte não encontrado"));

        return ResponseEntity.ok(Mapper.parseTo(transporte, MonitoramentoTransporteDTO.class));
    }

    @Override
    public ResponseEntity<List<MonitoramentoTransporteDTO>> buscarTodosTransportesBOT() {
        var dtos = Mapper.parseListTo(monitoramentoTransporteRepository
                .findAll(), MonitoramentoTransporteDTO.class);

        return ResponseEntity.ok(dtos);
    }
}
