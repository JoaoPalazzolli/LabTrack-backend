package br.com.project.labtrack.service;

import br.com.project.labtrack.dto.MonitoramentoTransporteDTO;
import br.com.project.labtrack.infra.utils.StatusTransporte;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface MonitoramentoTransporteService {

    ResponseEntity<Void> addTransporte(MonitoramentoTransporteDTO transporteDTO);
    ResponseEntity<Void> atualizarStatus(UUID codigoTransporte, StatusTransporte statusTransporte);
    ResponseEntity<Void> atualizarUsuarioRecebido(UUID codigoTransporte);
    ResponseEntity<MonitoramentoTransporteDTO> buscarTransportePorId(UUID codigoTransporte);
    ResponseEntity<List<MonitoramentoTransporteDTO>> buscarTodosTransportesPorUsuario();
}
