package com.example.ProyectoIntegradorClinica.controller.restcontroller;

import com.example.ProyectoIntegradorClinica.dto.TurnoDto;
import com.example.ProyectoIntegradorClinica.exceptions.BadRequestException;
import com.example.ProyectoIntegradorClinica.exceptions.ResourceNotFoundException;
import com.example.ProyectoIntegradorClinica.service.imp.TurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    TurnoService turnoService;

    private final Logger logger = Logger.getLogger(PacienteController.class);

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/todos")
    public ResponseEntity<List<TurnoDto>> consultarTodos() {
        logger.debug("Iniciando el método 'consultarTodos'");
        return ResponseEntity.ok(turnoService.consultarTodos());
    }

    @GetMapping("/buscarId/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Integer id) throws ResourceNotFoundException {

        logger.debug("Iniciando el método 'buscarPorId");

        if(turnoService.buscar(id) != null){
            logger.debug("Se encontró el turno");
            return ResponseEntity.ok(turnoService.buscar(id));
        }
        logger.debug("No se encontró el turno");
        throw new ResourceNotFoundException("No se encontró el turno");
    }

    @GetMapping("/proximaSemana")
    public ResponseEntity<List<TurnoDto>> consultarProximosTurnos() {
        logger.debug("Iniciando el método 'consultarProximosTurnos'");
        return ResponseEntity.ok(turnoService.consultarProximosTurnos());
    }

    @PostMapping("/nuevo")
    public ResponseEntity<TurnoDto> crearNuevoTurno(@RequestBody TurnoDto turno){
        logger.debug("Iniciando el método 'crearNuevo (turno)'");
        return ResponseEntity.ok(turnoService.crear(turno));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarTurno(@RequestBody TurnoDto turno) throws BadRequestException, ResourceNotFoundException {

        logger.debug("Iniciando el método 'actualizar(turno)'");
        Integer idTurno = turno.getId();
        if (idTurno == null) {
            throw new BadRequestException("Es necesario el id del turno");
        } else {
            if (turnoService.buscar(idTurno) != null) {
                logger.debug("Se pudo actualizar el turno");
                return ResponseEntity.ok(turnoService.actualizar(turno));
            }
            throw new ResourceNotFoundException("No se encontró el turno con el id " + idTurno);

        }

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/eliminarId/{id}")
    public ResponseEntity<String> eliminarPorId(@PathVariable("id") Integer id) throws ResourceNotFoundException {

        logger.debug("Iniciando el método 'eliminarPorId'");

        if(turnoService.buscar(id) != null){
            turnoService.eliminar(id);
            logger.debug("Se eliminó el turno con id "+id);
            return ResponseEntity.ok("Se eliminó el turno con id "+id);
        }else{
            logger.debug("No se encontro el turno con id "+id);
            throw new ResourceNotFoundException("No se encontro el turno con el id " + id);
        }
    }
}
