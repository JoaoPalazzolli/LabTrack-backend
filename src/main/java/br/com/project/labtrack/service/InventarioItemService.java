package br.com.project.labtrack.service;

import br.com.project.labtrack.dto.InventarioItemDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface InventarioItemService {

    ResponseEntity<List<InventarioItemDTO>> buscarTodosItens();
    ResponseEntity<InventarioItemDTO> buscarItemPorId(UUID codigoItem);
    ResponseEntity<Void> adicionarItem(InventarioItemDTO itemDTO);
    ResponseEntity<Void> atualizarItem(UUID codigoItem, InventarioItemDTO itemDTO);
    ResponseEntity<Void> deletarItem(UUID codigoItem);
    ResponseEntity<List<InventarioItemDTO>> pesquisarItens(String search);
}
