package br.com.project.labtrack.controller;

import br.com.project.labtrack.dto.InventarioItemDTO;
import br.com.project.labtrack.service.InventarioItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/inventario")
public class InventarioItemController {

    @Autowired
    private InventarioItemService inventarioItemService;

    @GetMapping
    public ResponseEntity<List<InventarioItemDTO>> buscarTodosOsItens(){
        return inventarioItemService.buscarTodosOsItens();
    }

    @GetMapping(value = "/{itemId}")
    public ResponseEntity<InventarioItemDTO> buscarItemPorId(@PathVariable(value = "itemId") UUID itemId){
        return inventarioItemService.buscarItemPorId(itemId);
    }

    @PostMapping
    public ResponseEntity<Void> adicionarItem(@RequestBody InventarioItemDTO itemDTO){
        return inventarioItemService.adicionarItem(itemDTO);
    }

    @PutMapping(value = "/{itemId}")
    public ResponseEntity<Void> atualizarItem(
            @PathVariable(value = "itemId") UUID itemId,
            @RequestBody InventarioItemDTO itemDTO){
        return inventarioItemService.atualizarItem(itemId, itemDTO);
    }

    @DeleteMapping(value = "/{itemId}")
    public ResponseEntity<Void> deletarItem(@PathVariable(value = "itemId") UUID itemId){
        return inventarioItemService.deletarItem(itemId);
    }
}
