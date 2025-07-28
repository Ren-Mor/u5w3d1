package Ren_Mor.U5_W3_D1.repositories;

import Ren_Mor.U5_W3_D1.entities.Dipendente;
import Ren_Mor.U5_W3_D1.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Integer> {
    boolean existsByDipendenteAndDataRichiestaPrenotazione(Dipendente dipendente, LocalDate dataRichiestaPrenotazione);
}