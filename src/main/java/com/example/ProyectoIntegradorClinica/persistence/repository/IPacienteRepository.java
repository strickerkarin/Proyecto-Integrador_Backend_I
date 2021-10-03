package com.example.ProyectoIntegradorClinica.persistence.repository;


import com.example.ProyectoIntegradorClinica.dto.PacienteDto;
import com.example.ProyectoIntegradorClinica.persistence.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.Locale;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Integer > {


}
