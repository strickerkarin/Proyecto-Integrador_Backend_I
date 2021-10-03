package com.example.ProyectoIntegradorClinica.persistence.repository;

import com.example.ProyectoIntegradorClinica.persistence.entities.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDomiclioRepository extends JpaRepository<Domicilio, Integer> {
}
