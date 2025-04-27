package br.com.project.labtrack.service.impl;

import br.com.project.labtrack.dto.MonitoramentoTransporteDTO;
import br.com.project.labtrack.infra.utils.Mapper;
import br.com.project.labtrack.infra.utils.StatusTransporte;
import br.com.project.labtrack.infra.utils.UsuarioAutenticado;
import br.com.project.labtrack.repository.MonitoramentoTransporteRepository;
import br.com.project.labtrack.service.MonitoramentoTransporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MonitoramentoTransporteServiceImpl implements MonitoramentoTransporteService {

    @Autowired
    private MonitoramentoTransporteRepository monitoramentoTransporteRepository;

    @Override
    public ResponseEntity<Void> addTransporte(MonitoramentoTransporteDTO transporteDTO) {

        // alterações a ser feitas ainda
        return null;
    }

    @Override
    public ResponseEntity<Void> atualizarStatus(UUID transporteId, StatusTransporte statusTransporte) {

        monitoramentoTransporteRepository.updateStatusTransporte(transporteId, statusTransporte);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> atualizarUsuarioRecebido(UUID transporteId) {
        var user = UsuarioAutenticado.pegarUsuarioAutenticado();

        monitoramentoTransporteRepository.updateUsuarioRecebido(transporteId, user);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<MonitoramentoTransporteDTO> buscarTransportePorId(UUID transporteId) {
        var transporte = monitoramentoTransporteRepository.findById(transporteId)
                .orElseThrow(() -> new RuntimeException("Transporte não encontrado")); // arrumar

        return ResponseEntity.ok(Mapper.parseTo(transporte, MonitoramentoTransporteDTO.class));
    }

    @Override
    public ResponseEntity<List<MonitoramentoTransporteDTO>> buscarTodosTransportesPorUsuario() {
        var user = UsuarioAutenticado.pegarUsuarioAutenticado();

        var dtos = Mapper.parseListTo(monitoramentoTransporteRepository
                .findAllTransporteByUsuarioId(user.getId()), MonitoramentoTransporteDTO.class);

        return ResponseEntity.ok(dtos);
    }
}
