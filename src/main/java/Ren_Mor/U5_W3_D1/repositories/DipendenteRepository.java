package Ren_Mor.U5_W3_D1.repositories;



import Ren_Mor.U5_W3_D1.entities.Dipendente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DipendenteRepository extends JpaRepository<Dipendente, Integer> {
    Optional<Object> findByEmail(@Email @NotBlank(message = "Non può essere vuoto") String email);
}
