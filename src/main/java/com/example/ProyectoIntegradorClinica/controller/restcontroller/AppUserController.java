package com.example.ProyectoIntegradorClinica.controller.restcontroller;

import com.example.ProyectoIntegradorClinica.persistence.entities.AppUser;
import com.example.ProyectoIntegradorClinica.service.imp.AppUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class AppUserController {

    @Autowired
    AppUserService appUserService;
    private final Logger logger = Logger.getLogger(OdontologoController.class);

    @PostMapping("/nuevo")
    public ResponseEntity<AppUser> crearNuevo(@RequestBody AppUser user){

        logger.debug("Iniciando el método 'crearNuevo (App user)'");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        String encodedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);
        return ResponseEntity.ok(appUserService.crear(user));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<AppUser>> consultarTodos() {
        logger.debug("Iniciando el método 'consultarTodos'");
        return ResponseEntity.ok(appUserService.consultarTodos());
    }



}
