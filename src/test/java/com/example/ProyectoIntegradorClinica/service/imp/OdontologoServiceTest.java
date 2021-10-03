package com.example.ProyectoIntegradorClinica.service.imp;

import com.example.ProyectoIntegradorClinica.dto.OdontologoDto;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

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
        Assert.assertTrue(odontologoCreado.getId() != null);
    }

    @Test
    void buscar() {
        OdontologoDto odontologoEncontrado = odontologoService.buscar(1);
        Assert.assertTrue(odontologoEncontrado.getNombre() == "Lisandro");
    }

    @Test

    void actualizar() {
        odontologo.setNombre("Ezequiel");
        OdontologoDto odontologoActualizado = odontologoService.actualizar(odontologo);
        Assert.assertTrue(odontologoActualizado.getNombre() == "Ezequiel");
    }


    @Test
    void consultarTodos() {
        Assert.assertTrue(odontologoService.consultarTodos().size() != 0);
    }

    @Order(4)
    @Test
    void eliminar() {
        odontologoService.eliminar(1);
        Assert.assertTrue(odontologoService.buscar(1) == null);
    }


}