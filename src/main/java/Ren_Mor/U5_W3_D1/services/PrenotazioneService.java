package Ren_Mor.U5_W3_D1.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Ren_Mor.U5_W3_D1.entities.Dipendente;
import Ren_Mor.U5_W3_D1.entities.Prenotazione;
import Ren_Mor.U5_W3_D1.entities.Viaggio;
import Ren_Mor.U5_W3_D1.exceptions.BadRequestException;
import Ren_Mor.U5_W3_D1.exceptions.ResourceNotFoundException;
import Ren_Mor.U5_W3_D1.payloads.PrenotazioneDTO;
import Ren_Mor.U5_W3_D1.repositories.DipendenteRepository;
import Ren_Mor.U5_W3_D1.repositories.PrenotazioneRepository;
import Ren_Mor.U5_W3_D1.repositories.ViaggioRepository;

import java.util.List;

@Service
@Slf4j
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private ViaggioRepository viaggioRepository;

    public Prenotazione save(PrenotazioneDTO body) {
        Dipendente dipendente = dipendenteRepository.findById(body.dipendenteId())
                .orElseThrow(() -> new ResourceNotFoundException("Dipendente non trovato"));
        Viaggio viaggio = viaggioRepository.findById(body.ViaggioId())
                .orElseThrow(() -> new ResourceNotFoundException("Viaggio non trovato"));

        if (prenotazioneRepository.existsByDipendenteAndDataRichiestaPrenotazione(dipendente, body.dataRichiesta())) {
            throw new BadRequestException("Dipendente già impegnato in questa data");
        }

        Prenotazione prenotazione = new Prenotazione(viaggio, dipendente, body.dataRichiesta());
        Prenotazione saved = prenotazioneRepository.save(prenotazione);
        log.info("Prenotazione con id: " + saved.getId() + " salvata correttamente!");
        return saved;
    }

    public List<Prenotazione> getAll() {
        return prenotazioneRepository.findAll();
    }

    public Prenotazione findById(int id) {
        return prenotazioneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prenotazione non trovata"));
    }

    public void delete(int id) {
        Prenotazione found = findById(id);
        prenotazioneRepository.delete(found);
    }

    public Prenotazione update(int id, PrenotazioneDTO body) {
        Prenotazione prenotazione = findById(id);
        Dipendente dipendente = dipendenteRepository.findById(body.dipendenteId())
                .orElseThrow(() -> new ResourceNotFoundException("Dipendente non trovato"));
        Viaggio viaggio = viaggioRepository.findById(body.ViaggioId())
                .orElseThrow(() -> new ResourceNotFoundException("Viaggio non trovato"));

        if (prenotazioneRepository.existsByDipendenteAndDataRichiestaPrenotazione(dipendente, body.dataRichiesta())
                && prenotazione.getDipendente().getId() != dipendente.getId()) {
            throw new BadRequestException("Dipendente già impegnato in questa data");
        }

        prenotazione.setDipendente(dipendente);
        prenotazione.setViaggio(viaggio);
        prenotazione.setDataRichiestaPrenotazione(body.dataRichiesta());
        Prenotazione modified = prenotazioneRepository.save(prenotazione);
        log.info("Prenotazione con id: " + prenotazione.getId() + " modificata!");
        return modified;
    }
}