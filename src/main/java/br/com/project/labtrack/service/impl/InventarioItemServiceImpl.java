package br.com.project.labtrack.service.impl;

import br.com.project.labtrack.domain.InventarioItem;
import br.com.project.labtrack.dto.InventarioItemDTO;
import br.com.project.labtrack.infra.exceptions.GenerationFailedException;
import br.com.project.labtrack.infra.exceptions.ObjectNotFound;
import br.com.project.labtrack.infra.cloudinary.CloudinaryService;
import br.com.project.labtrack.utils.Mapper;
import br.com.project.labtrack.infra.qrcode.QRCodeGenerator;
import br.com.project.labtrack.infra.utils.UsuarioAutenticado;
import br.com.project.labtrack.repository.InventarioItemRepository;
import br.com.project.labtrack.service.InventarioItemService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class InventarioItemServiceImpl implements InventarioItemService {

    @Autowired
    private InventarioItemRepository inventarioItemRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public ResponseEntity<List<InventarioItemDTO>> buscarTodosItens() {
        var user = UsuarioAutenticado.pegarUsuarioAutenticado();

        var dtos = Mapper.parseListTo(
                inventarioItemRepository.findAllByUsuarioId(user.getId()), InventarioItemDTO.class);

        return ResponseEntity.ok(dtos);
    }

    @Override
    public ResponseEntity<InventarioItemDTO> buscarItemPorId(UUID codigoItem) {
        var user = UsuarioAutenticado.pegarUsuarioAutenticado();

        var item = inventarioItemRepository.findByCodigoItemAndUsuarioId(codigoItem, user.getId())
                .orElseThrow(() -> new ObjectNotFound("Item não encontrado"));

        var dto = Mapper.parseTo(item, InventarioItemDTO.class);

        return ResponseEntity.ok(dto);
    }

    @Transactional
    @Override
    public ResponseEntity<Void> adicionarItem(InventarioItemDTO itemDTO) {
        var item = Mapper.parseTo(itemDTO, InventarioItem.class);

        var user = UsuarioAutenticado.pegarUsuarioAutenticado();

        item.setUsuario(user);

        inventarioItemRepository.save(item);

        try{
            inventarioItemRepository.updateQrCodeImageUrlItem(item.getCodigoItem(),
                    cloudinaryService.uploadQRCode(QRCodeGenerator.generate("Em manutenção...", 300, 300), item.getCodigoItem().toString()));
        } catch (Exception e){
            throw new GenerationFailedException("Erro ao gerar QR Code: " + e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Transactional
    @Override
    public ResponseEntity<Void> atualizarItem(UUID codigoItem, InventarioItemDTO itemDTO) {
        var user = UsuarioAutenticado.pegarUsuarioAutenticado();

        var item = inventarioItemRepository.findByCodigoItemAndUsuarioId(codigoItem, user.getId())
                .orElseThrow(() -> new ObjectNotFound("Item não encontrado"));

        var qrCode = item.getQrCodeImageUrl();

        item = Mapper.parseTo(itemDTO, InventarioItem.class);

        item.setCodigoItem(codigoItem);
        item.setUsuario(user);
        item.setUltimaAlteracaoData(LocalDateTime.now());
        item.setQrCodeImageUrl(qrCode);

        inventarioItemRepository.save(item);

        return ResponseEntity.noContent().build();
    }

    @Transactional
    @Override
    public ResponseEntity<Void> deletarItem(UUID codigoItem) {
        var user = UsuarioAutenticado.pegarUsuarioAutenticado();

        var item = inventarioItemRepository.findByCodigoItemAndUsuarioId(codigoItem, user.getId())
                .orElseThrow(() -> new ObjectNotFound("Item não encontrado"));

        inventarioItemRepository.delete(item);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<InventarioItemDTO>> pesquisarItens(String search) {
        var user = UsuarioAutenticado.pegarUsuarioAutenticado();

        var itens = inventarioItemRepository.searchByDescricao(user.getId(), search);

        var dtos = Mapper.parseListTo(itens, InventarioItemDTO.class);

        return ResponseEntity.ok(dtos);
    }

    @Transactional
    @Override
    public ResponseEntity<Void> atualizarQuantidade(UUID codigoItem, Double quantidade) {
        var user = UsuarioAutenticado.pegarUsuarioAutenticado();

        inventarioItemRepository.updateQuantidadeItem(codigoItem, user.getId(), quantidade);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<InventarioItemDTO>> buscarTodosItensBOT() {
        var dtos = Mapper.parseListTo(
                inventarioItemRepository.findAll(), InventarioItemDTO.class);

        return ResponseEntity.ok(dtos);
    }

    @Override
    public ResponseEntity<List<InventarioItemDTO>> pesquisarItensBOT(String search) {
        var itens = inventarioItemRepository.searchByDescricao(search);

        var dtos = Mapper.parseListTo(itens, InventarioItemDTO.class);

        return ResponseEntity.ok(dtos);
    }

    @Override
    public ResponseEntity<InventarioItemDTO> buscarItemPorIdBOT(UUID codigoItem) {
        var item = inventarioItemRepository.findById(codigoItem)
                .orElseThrow(() -> new ObjectNotFound("Item não encontrado"));

        var dto = Mapper.parseTo(item, InventarioItemDTO.class);

        return ResponseEntity.ok(dto);
    }
}
