package com.example.ProyectoIntegradorClinica.controller.restcontroller;


import com.example.ProyectoIntegradorClinica.dto.PacienteDto;
import com.example.ProyectoIntegradorClinica.exceptions.BadRequestException;
import com.example.ProyectoIntegradorClinica.exceptions.ResourceNotFoundException;
import com.example.ProyectoIntegradorClinica.service.imp.PacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.module.ResolutionException;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    private final Logger logger = Logger.getLogger(PacienteController.class);

    @GetMapping("/buscarId/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Integer id) throws ResourceNotFoundException {

        logger.debug("Iniciando el método 'buscarPorId");
        PacienteDto pacienteBuscado = pacienteService.buscar(id);
        if(pacienteBuscado != null){
            logger.debug("Se encontró el paciente");
            return ResponseEntity.ok(pacienteBuscado);
        }
        logger.debug("No se encontró el paciente");
        throw new ResourceNotFoundException("No se encontró al paciente con id " + id);

    }

    @GetMapping("/todos")
    public ResponseEntity<List<PacienteDto>> consultarTodos() {
        logger.debug("Iniciando el método 'consultarTodos'");
        return ResponseEntity.ok(pacienteService.consultarTodos());
    }

    @PostMapping("/nuevo")
    public ResponseEntity<PacienteDto> crearNuevoPaciente(@RequestBody PacienteDto paciente){
        logger.debug("Iniciando el método 'crearNuevo (paciente)'");
        return ResponseEntity.ok(pacienteService.crear(paciente));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarPaciente(@RequestBody PacienteDto paciente) throws ResourceNotFoundException, BadRequestException {
        logger.debug("Iniciando el método 'actualizar(paciente)'");
        Integer idPaciente = paciente.getId();
        if (idPaciente == null) {
            throw new BadRequestException("Es necesario el id del paciente");
        } else {
            if (pacienteService.buscar(idPaciente) != null) {
                return ResponseEntity.ok(pacienteService.actualizar(paciente));
            }
            throw new ResourceNotFoundException("No se encontró el paciente con el id " + idPaciente);
        }
    }

    @DeleteMapping("/eliminarId/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable("id") Integer id) throws ResourceNotFoundException{
        logger.debug("Iniciando el método 'eliminarPorId'");

        if(pacienteService.buscar(id) != null){
          pacienteService.eliminar(id);
          logger.debug("Se eliminó el paciente con id "+id);
          return ResponseEntity.ok("Se eliminó el paciente con id "+ id);
        }
        logger.debug("No se encontró el paciente con id "+id);
        throw new ResourceNotFoundException("No se encontró el paciente con el id " + id);
    }

}
