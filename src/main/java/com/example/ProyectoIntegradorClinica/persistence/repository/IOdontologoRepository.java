package com.example.ProyectoIntegradorClinica.persistence.repository;

import com.example.ProyectoIntegradorClinica.persistence.entities.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface IOdontologoRepository extends JpaRepository<Odontologo, Integer> {
}
