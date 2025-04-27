package br.com.project.labtrack.service;

import br.com.project.labtrack.dto.InventarioItemDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface InventarioItemService {

    ResponseEntity<List<InventarioItemDTO>> buscarTodosOsItens();
    ResponseEntity<InventarioItemDTO> buscarItemPorId(UUID itemId);
    ResponseEntity<Void> adicionarItem(InventarioItemDTO itemDTO);
    ResponseEntity<Void> atualizarItem(UUID itemId, InventarioItemDTO itemDTO);
    ResponseEntity<Void> deletarItem(UUID itemId);
}
