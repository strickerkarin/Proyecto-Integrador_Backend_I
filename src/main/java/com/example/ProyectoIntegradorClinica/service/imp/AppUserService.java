package com.example.ProyectoIntegradorClinica.service.imp;



import com.example.ProyectoIntegradorClinica.dto.TurnoDto;
import com.example.ProyectoIntegradorClinica.persistence.entities.AppUser;
import com.example.ProyectoIntegradorClinica.persistence.entities.Turno;
import com.example.ProyectoIntegradorClinica.persistence.repository.IUserRepository;
import com.example.ProyectoIntegradorClinica.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppUserService implements UserDetailsService {

    private final IUserRepository userRepository;

    @Autowired
    public AppUserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email){
        return userRepository.findByEmail(email);
    }


    public AppUser crear(AppUser user) {
        return userRepository.save(user);
    }


    public List<AppUser> consultarTodos() {
        List <AppUser> usuarios = new ArrayList<>();
        for( AppUser u : userRepository.findAll()){
            usuarios.add(u);
        }
        return  usuarios;
    }
}
