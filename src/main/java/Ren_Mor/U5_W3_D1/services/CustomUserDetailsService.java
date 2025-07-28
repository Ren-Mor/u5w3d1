package Ren_Mor.U5_W3_D1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import Ren_Mor.U5_W3_D1.entities.Dipendente;
import Ren_Mor.U5_W3_D1.repositories.DipendenteRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Dipendente user = dipendenteRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato"));
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities("USER")
                .build();
    }
}

