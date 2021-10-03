package com.example.ProyectoIntegradorClinica.securityConfig;


import com.example.ProyectoIntegradorClinica.persistence.entities.AppUser;
import com.example.ProyectoIntegradorClinica.persistence.entities.AppUserRole;
import com.example.ProyectoIntegradorClinica.persistence.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private IUserRepository userRepository;



    @Autowired
    public DataLoader(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("digital");
        userRepository.save(new AppUser("Karin", "strickerkarin", "karin@digital.com",
                hashedPassword, AppUserRole.ADMIN));
    }
}
