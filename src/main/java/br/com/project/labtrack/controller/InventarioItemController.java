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
    public ResponseEntity<List<InventarioItemDTO>> buscarTodosItens(){
        return inventarioItemService.buscarTodosItens();
    }

    @GetMapping(params = "search")
    public ResponseEntity<List<InventarioItemDTO>> pesquisarItens(@RequestParam(defaultValue = "") String search){
        return inventarioItemService.pesquisarItens(search);
    }

    @GetMapping(value = "/{codigoItem}")
    public ResponseEntity<InventarioItemDTO> buscarItemPorId(@PathVariable(value = "codigoItem") UUID codigoItem){
        return inventarioItemService.buscarItemPorId(codigoItem);
    }

    @PostMapping
    public ResponseEntity<Void> adicionarItem(@RequestBody InventarioItemDTO itemDTO){
        return inventarioItemService.adicionarItem(itemDTO);
    }

    @PutMapping(value = "/{codigoItem}")
    public ResponseEntity<Void> atualizarItem(
            @PathVariable(value = "codigoItem") UUID codigoItem,
            @RequestBody InventarioItemDTO itemDTO){
        return inventarioItemService.atualizarItem(codigoItem, itemDTO);
    }

    @PatchMapping(value = "/{codigoItem}/quantidade/{quantidade}")
    public ResponseEntity<Void> atualizarQuantidade(@PathVariable UUID codigoItem, @PathVariable Double quantidade){
        return inventarioItemService.atualizarQuantidade(codigoItem, quantidade);
    }

    @DeleteMapping(value = "/{codigoItem}")
    public ResponseEntity<Void> deletarItem(@PathVariable(value = "codigoItem") UUID codigoItem){
        return inventarioItemService.deletarItem(codigoItem);
    }

    @GetMapping(value = "/chatbot")
    public ResponseEntity<List<InventarioItemDTO>> buscarTodosItensBOT(){
        return inventarioItemService.buscarTodosItensBOT();
    }

    @GetMapping(value = "/chatbot", params = "search")
    public ResponseEntity<List<InventarioItemDTO>> pesquisarItensBOT(@RequestParam(defaultValue = "") String search){
        return inventarioItemService.pesquisarItensBOT(search);
    }

    @GetMapping(value = "/chatbot/{codigoItem}")
    public ResponseEntity<InventarioItemDTO> buscarItemPorIdBOT(@PathVariable(value = "codigoItem") UUID codigoItem){
        return inventarioItemService.buscarItemPorIdBOT(codigoItem);
    }
}
