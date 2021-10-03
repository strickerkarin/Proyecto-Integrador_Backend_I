package com.example.ProyectoIntegradorClinica.service.imp;


import com.example.ProyectoIntegradorClinica.dto.TurnoDto;
import com.example.ProyectoIntegradorClinica.persistence.entities.Turno;
import com.example.ProyectoIntegradorClinica.persistence.repository.ITurnoRepository;
import com.example.ProyectoIntegradorClinica.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class TurnoService implements IService<TurnoDto> {


    @Autowired
    ITurnoRepository repository;

    @Autowired
    OdontologoService serviceOdontologo;

    @Autowired
    PacienteService servicePaciente;

    @Override
    public TurnoDto buscar(Integer id) {
        if(repository.existsById(id)) {
            return new TurnoDto(repository.getById(id));
        }else{
            return null;
        }
    }


    @Override
    public TurnoDto actualizar(TurnoDto turnoDto) {
        boolean checkQueExisteElTurno = (buscar(turnoDto.getId())!= null);
        boolean checkDisponibilidad = verificarDisponibilidad(turnoDto);
        if(checkQueExisteElTurno && checkDisponibilidad) {
            turnoDto.setPaciente(servicePaciente.buscar(turnoDto.getPaciente().getId()));
            turnoDto.setOdontologo(serviceOdontologo.buscar(turnoDto.getOdontologo().getId()));
            return new TurnoDto(repository.save(turnoDto.toEntity()));
        }
        return null;
    }

    @Override
    public void eliminar(Integer id) {
        repository.delete(repository.getById(id));
    }

    @Override
    public List<TurnoDto> consultarTodos() {
        List<TurnoDto> turnos = new ArrayList<>();

        for(Turno t : repository.findAll()){
            turnos.add(new TurnoDto(t));
        }
        return turnos;
    }

    public List<TurnoDto> consultarProximosTurnos() {
        List<TurnoDto> turnosProximaSemana = new ArrayList<>();
        for(Turno t : repository.findAll()){
            if(t.getFechaYHora().isBefore(LocalDateTime.now().plusWeeks(1)) && t.getFechaYHora().isAfter(LocalDateTime.now())) {
                turnosProximaSemana.add(new TurnoDto(t));
            }
        }
        return turnosProximaSemana;
    }

    @Override
    public TurnoDto crear(TurnoDto turnoDto) {

        Integer pacienteId = turnoDto.getPaciente().getId();
        Integer odontologoId = turnoDto.getOdontologo().getId();

        if(this.verficarTurno(pacienteId,odontologoId,turnoDto.getFechaYHora()) && this.verificarDisponibilidad(turnoDto)){
            turnoDto.setPaciente(servicePaciente.buscar(pacienteId));
            turnoDto.setOdontologo(serviceOdontologo.buscar(odontologoId));
            Turno turnoGuardado = repository.save(turnoDto.toEntity());
            turnoDto.setId(turnoGuardado.getId());

            return turnoDto;
        }
            //Exception ("El odontólogo y/o el paciente no existen")
        return null;
    }

    private boolean verficarTurno(Integer pacienteId, Integer odontologoId, LocalDateTime fechaYHora){

        return (servicePaciente.buscar(pacienteId) != null && serviceOdontologo.buscar(odontologoId) !=null && fechaYHora != null);
    }

    private boolean verificarDisponibilidad(TurnoDto turnoNuevo){
        boolean check = true;
        for (TurnoDto turno: this.consultarTodos()) {
             if(turno.equals(turnoNuevo)){
                return check = false;
             }
        }
        return check;
    }


}
