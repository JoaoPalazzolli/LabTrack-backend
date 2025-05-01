package br.com.project.labtrack.controller;

import br.com.project.labtrack.dto.MonitoramentoTransporteDTO;
import br.com.project.labtrack.infra.utils.StatusTransporte;
import br.com.project.labtrack.service.MonitoramentoTransporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transporte")
public class MonitoramentoTransporteController {

    @Autowired
    private MonitoramentoTransporteService monitoramentoTransporteService;

    @PostMapping
    public ResponseEntity<Void> addTransporte(@RequestBody MonitoramentoTransporteDTO transporteDTO){
        return monitoramentoTransporteService.addTransporte(transporteDTO);
    }

    @PatchMapping(value = "/{codigoTransporte}/status/{status}")
    public ResponseEntity<Void> atualizarStatus(
            @PathVariable(value = "codigoTransporte") UUID codigoTransporte,
            @PathVariable(value = "status") StatusTransporte statusTransporte){
        return monitoramentoTransporteService.atualizarStatus(codigoTransporte, statusTransporte);
    }

    @PatchMapping(value = "/{codigoTransporte}")
    public ResponseEntity<Void> atualizarUsuarioRecebido(
            @PathVariable(value = "codigoTransporte") UUID codigoTransporte){
        return monitoramentoTransporteService.atualizarUsuarioRecebido(codigoTransporte);
    }

    @GetMapping(value = "/{codigoTransporte}")
    public ResponseEntity<MonitoramentoTransporteDTO> buscarTransportePorId(
            @PathVariable(value = "codigoTransporte") UUID codigoTransporte){
        return monitoramentoTransporteService.buscarTransportePorId(codigoTransporte);
    }

    @GetMapping
    public ResponseEntity<List<MonitoramentoTransporteDTO>> buscarTodosTransportesPorUsuario(){
        return monitoramentoTransporteService.buscarTodosTransportesPorUsuario();
    }
}
