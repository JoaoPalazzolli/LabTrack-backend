package br.com.project.labtrack.controller;

import br.com.project.labtrack.dto.InventarioItemDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/inventario")
public class InventarioItemController {

    @GetMapping
    public ResponseEntity<List<InventarioItemDTO>> buscarTodosOsItens(){
        return null;
    }

    @GetMapping(value = "/{itemId}")
    public ResponseEntity<InventarioItemDTO> buscarItemPorId(@PathVariable(value = "itemId") UUID itemId){
        return null;
    }

    @PostMapping
    public ResponseEntity<Void> adicionarItem(@RequestBody InventarioItemDTO itemDTO){
        return null;
    }

    @PutMapping(value = "/{itemId}")
    public ResponseEntity<Void> atualizarItem(
            @PathVariable(value = "itemId") UUID itemId,
            @RequestBody InventarioItemDTO itemDTO){
        return null;
    }

    @DeleteMapping(value = "/{itemId}")
    public ResponseEntity<InventarioItemDTO> deletarItem(@PathVariable(value = "itemId") UUID itemId){
        return null;
    }
}
