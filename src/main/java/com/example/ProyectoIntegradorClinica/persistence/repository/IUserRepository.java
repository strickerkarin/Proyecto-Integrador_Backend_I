package com.example.ProyectoIntegradorClinica.persistence.repository;


import com.example.ProyectoIntegradorClinica.persistence.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface IUserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByEmail(String email);
}
