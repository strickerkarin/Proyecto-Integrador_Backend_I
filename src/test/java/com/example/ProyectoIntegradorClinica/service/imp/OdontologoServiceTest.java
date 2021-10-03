package com.example.ProyectoIntegradorClinica.service.imp;

import com.example.ProyectoIntegradorClinica.dto.OdontologoDto;
import com.example.ProyectoIntegradorClinica.persistence.entities.Odontologo;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OdontologoServiceTest {

    @Autowired
    OdontologoService odontologoService;

    private OdontologoDto odontologo;
    private OdontologoDto odontologoCreado;

    @BeforeEach
    void setUp() {
        odontologo = new OdontologoDto();
        odontologo.setNombre("Lisandro");
        odontologo.setApellido("Albornoz");
        odontologo.setMatricula("AAA999");
        odontologoCreado = odontologoService.crear(odontologo);
    }

    @Test
    void crear() {
        odontologoCreado = odontologoService.crear(odontologo);
        Assert.assertTrue(odontologoCreado.getId() != null);
    }

    /* @Test
    void buscar() {
        //OdontologoDto odontologoEncontrado = odontologoService.buscar(1);
        Assert.assertTrue(odontologoEncontrado) == "Lisandro");
    } */

    @Test
    void actualizar() {
        odontologo.setNombre("Ezequiel");
        OdontologoDto odontologoActualizado = odontologoService.actualizar(odontologo);
        Assert.assertTrue(odontologoActualizado.getNombre() == "Ezequiel");
    }

    @Test
    void eliminar() {
        odontologoService.eliminar(1);
        Assert.assertTrue(odontologoService.buscar(1) == null);
    }

    @Test
    void consultarTodos() {
        Assert.assertTrue(odontologoService.consultarTodos().size() == 1);
    }
}