package com.example.ProyectoIntegradorClinica.persistence.repository;

import com.example.ProyectoIntegradorClinica.persistence.entities.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

public interface ITurnoRepository extends JpaRepository<Turno, Integer> {
}
