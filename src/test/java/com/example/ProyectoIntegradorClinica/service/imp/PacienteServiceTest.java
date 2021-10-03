package com.example.ProyectoIntegradorClinica.service.imp;

import com.example.ProyectoIntegradorClinica.dto.DomicilioDto;
import com.example.ProyectoIntegradorClinica.dto.PacienteDto;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
class PacienteServiceTest {

    @Autowired
    PacienteService pacienteService;

    private PacienteDto paciente;
    private DomicilioDto domicilioPaciente;
    private PacienteDto pacienteCreado;

    @BeforeEach
    void setUp() {
        domicilioPaciente = new DomicilioDto("Avenida A", "123", "Avellaneda", "Buenos Aires");
        paciente = new PacienteDto();
        paciente.setNombre("Maria");
        paciente.setApellido("Palacios");
        paciente.setDni("54222333");
        paciente.setDomicilio(domicilioPaciente);
        pacienteCreado = pacienteService.crear(paciente);
    }

    @Test
    void crear() {
        Assert.assertTrue(pacienteCreado.getId() != null);
    }

    @Test
    void buscar() {
        Assert.assertTrue(pacienteService.buscar(1).getNombre() == "Maria");
    }

    @Test
    void consultarTodos() {
        Assert.assertTrue(pacienteService.consultarTodos().size() != 0);
    }

    @Test
    void actualizar() {
        paciente.setNombre("Jeremias");
        PacienteDto pacienteActualizado = pacienteService.actualizar(paciente);
        Assert.assertTrue(pacienteActualizado.getNombre() == "Jeremias");
    }

    @Test
    void eliminar() {
        pacienteService.eliminar(1);
        Assert.assertTrue(pacienteService.buscar(1) == null);
    }
}