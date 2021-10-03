package com.example.ProyectoIntegradorClinica.controller.restcontroller;

import com.example.ProyectoIntegradorClinica.dto.OdontologoDto;
import com.example.ProyectoIntegradorClinica.exceptions.BadRequestException;
import com.example.ProyectoIntegradorClinica.exceptions.ResourceNotFoundException;
import com.example.ProyectoIntegradorClinica.service.imp.OdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    OdontologoService odontologoService;
    private final Logger logger = Logger.getLogger(OdontologoController.class);

    @GetMapping("/buscarId/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Integer id) throws ResourceNotFoundException {

        logger.debug("Iniciando el método 'buscarPorId");
        OdontologoDto odontologoBuscado = odontologoService.buscar(id);
        if(odontologoBuscado != null){
            return ResponseEntity.ok(odontologoBuscado);
        }else{
            logger.debug("No se encontró el odontologo");
           throw new ResourceNotFoundException("No se encontró el odontologo");

        }

    }

    @PostMapping("/nuevo")
    public ResponseEntity<OdontologoDto> crearNuevo(@RequestBody OdontologoDto odontologo){
        logger.debug("Iniciando el método 'crearNuevo (odontologo)'");
        return ResponseEntity.ok(odontologoService.crear(odontologo));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizar(@RequestBody OdontologoDto odontologo) throws ResourceNotFoundException, BadRequestException {

        logger.debug("Iniciando el método 'actualizar(odontologo)'");
        Integer idOdontologo = odontologo.getId();
        if (idOdontologo == null) {
            throw new BadRequestException("Es necesario el id del odontologo");
        } else {
            if (odontologoService.buscar(idOdontologo) != null) {
                return ResponseEntity.ok(odontologoService.actualizar(odontologo));
            }
            throw new ResourceNotFoundException("No se encontró el odontologo con el id " + idOdontologo);
        }
    }

    @DeleteMapping("/eliminarId/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable("id") Integer id) throws ResourceNotFoundException{

        logger.debug("Iniciando el método 'eliminarPorId'");

        if(odontologoService.buscar(id) != null){
            odontologoService.eliminar(id);
            return ResponseEntity.ok("Se eliminó el odontólogo con id " + id);
        }
        throw new ResourceNotFoundException("No se encontro el odontologo con el id " + id);

    }

    @GetMapping("/todos")
    public ResponseEntity<List<OdontologoDto>> consultarTodos(){
        logger.debug("Iniciando el método 'consultarTodos'");
        return ResponseEntity.ok(odontologoService.consultarTodos());
    }
}
