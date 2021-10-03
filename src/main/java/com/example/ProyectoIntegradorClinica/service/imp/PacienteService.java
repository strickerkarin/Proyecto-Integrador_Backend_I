package com.example.ProyectoIntegradorClinica.service.imp;

import com.example.ProyectoIntegradorClinica.dto.PacienteDto;
import com.example.ProyectoIntegradorClinica.persistence.entities.Paciente;
import com.example.ProyectoIntegradorClinica.persistence.repository.IPacienteRepository;
import com.example.ProyectoIntegradorClinica.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PacienteService implements IService<PacienteDto> {

    @Autowired
    IPacienteRepository repository;

    @Override
    public PacienteDto crear(PacienteDto p) {
        p.setFechaIngreso(LocalDate.now());
        Paciente pacienteGuardado = repository.save(p.toEntity());
        p.setId(pacienteGuardado.getId());
        p.getDomicilio().setId(pacienteGuardado.getDomicilio().getId());
        return p;
    }

    @Override
    public PacienteDto buscar(Integer id) {
        if(repository.existsById(id)) {
            return new PacienteDto(repository.getById(id));
        }
        return null;
    }

    @Override
    public List<PacienteDto> consultarTodos() {
        List<PacienteDto> pacientes = new ArrayList<>();
        for(Paciente p : repository.findAll()){
            pacientes.add(new PacienteDto(p));
        }
        return pacientes;
    }

    @Override
    public PacienteDto actualizar(PacienteDto p) {
        Paciente pacienteEnBD = repository.getById(p.getId());
        p.getDomicilio().setId(pacienteEnBD.getDomicilio().getId());
        repository.save(p.toEntity());
        return p;
    }

    @Override
    public void eliminar(Integer id) {
        repository.delete(repository.getById(id));
    }
}
