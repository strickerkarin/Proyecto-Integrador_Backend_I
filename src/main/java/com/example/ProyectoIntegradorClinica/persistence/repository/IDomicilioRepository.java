package com.example.ProyectoIntegradorClinica.persistence.repository;

import com.example.ProyectoIntegradorClinica.persistence.entities.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;


public interface IDomicilioRepository extends JpaRepository<Domicilio, Integer> {
}
