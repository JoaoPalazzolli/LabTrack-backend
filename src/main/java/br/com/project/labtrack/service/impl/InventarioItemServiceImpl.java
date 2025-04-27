package br.com.project.labtrack.service.impl;

import br.com.project.labtrack.domain.InventarioItem;
import br.com.project.labtrack.dto.InventarioItemDTO;
import br.com.project.labtrack.infra.utils.Mapper;
import br.com.project.labtrack.infra.utils.UsuarioAutenticado;
import br.com.project.labtrack.repository.InventarioItemRepository;
import br.com.project.labtrack.service.InventarioItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class InventarioItemServiceImpl implements InventarioItemService {

    @Autowired
    private InventarioItemRepository inventarioItemRepository;

    @Override
    public ResponseEntity<List<InventarioItemDTO>> buscarTodosOsItens() {
        var user = UsuarioAutenticado.pegarUsuarioAutenticado();

        var dtos = Mapper.parseListTo(
                inventarioItemRepository.findAllByUsuarioId(user.getId()), InventarioItemDTO.class);

        return ResponseEntity.ok(dtos);
    }

    @Override
    public ResponseEntity<InventarioItemDTO> buscarItemPorId(UUID itemId) {
        var user = UsuarioAutenticado.pegarUsuarioAutenticado();

        var item = inventarioItemRepository.findByIdAndUsuarioId(itemId, user.getId())
                .orElseThrow(() -> new RuntimeException("Item não encontrado")); // arrumar exception

        var dto = Mapper.parseTo(item, InventarioItemDTO.class);

        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<Void> adicionarItem(InventarioItemDTO itemDTO) {
        var item = Mapper.parseTo(itemDTO, InventarioItem.class);

        var user = UsuarioAutenticado.pegarUsuarioAutenticado();

        item.setUsuario(user);

        inventarioItemRepository.save(item);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> atualizarItem(UUID itemId, InventarioItemDTO itemDTO) {
        var user = UsuarioAutenticado.pegarUsuarioAutenticado();

        var item = inventarioItemRepository.findByIdAndUsuarioId(itemId, user.getId())
                .orElseThrow(() -> new RuntimeException("item não encontrado")); // arrumar

        item = Mapper.parseTo(itemDTO, InventarioItem.class);

        item.setUsuario(user);
        item.setUltimaAlteracaoData(LocalDateTime.now());

        inventarioItemRepository.save(item);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deletarItem(UUID itemId) {
        var user = UsuarioAutenticado.pegarUsuarioAutenticado();

        var item = inventarioItemRepository.findByIdAndUsuarioId(itemId, user.getId())
                .orElseThrow(() -> new RuntimeException("item não encontrado")); // arrumar

        inventarioItemRepository.delete(item);

        return ResponseEntity.noContent().build();
    }
}
