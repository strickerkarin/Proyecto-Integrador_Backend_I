package com.example.ProyectoIntegradorClinica.controller.restcontroller;

import com.example.ProyectoIntegradorClinica.Util.Jsons;
import com.example.ProyectoIntegradorClinica.dto.OdontologoDto;
import org.junit.Assert;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.example.ProyectoIntegradorClinica.exceptions.ResourceNotFoundException;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OdontologoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OdontologoController odontologoController;

    private final OdontologoDto od = new OdontologoDto("Lisandro", "Leon", "AAA999");

    @Order(1)
    @Test
    public void testRegistrarOdontologo() throws Exception {
        OdontologoDto respuesta = new OdontologoDto(1, "Lisandro", "Leon", "AAA999");

        MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.post("/odontologos/nuevo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Jsons.asJsonString(od)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Assert.assertEquals(Jsons.asJsonString(respuesta), response.getResponse().getContentAsString());
    }
    @Order(2)
    @Test
    public void testBuscarOdontologo() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/odontologos/buscarId/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Lisandro"));
    }
    @Order(3)
    @Test
    public void testBuscarOdontologoNoExistente() throws Exception {
        assertThrows(ResourceNotFoundException.class,() -> odontologoController.buscarPorId(2));
    }


    @Order(4)
    @Test
    public void testActualizarOdontologo() throws Exception {
        OdontologoDto respuesta = new OdontologoDto(1, "Roberto", "Leon", "AAA999");

        MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.put("/odontologos/actualizar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Jsons.asJsonString(respuesta)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Assert.assertEquals(Jsons.asJsonString(respuesta), response.getResponse().getContentAsString());
    }
    @Order(5)
    @Test
    public void testEliminarOdontologo() throws Exception {
        String respuesta = "Se eliminó el odontólogo con id 1";
        MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.delete("/odontologos/eliminarId/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Assert.assertEquals(respuesta, response.getResponse().getContentAsString());


    }






}