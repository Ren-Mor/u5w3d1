package Ren_Mor.U5_W3_D1.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Ren_Mor.U5_W3_D1.entities.Viaggio;
import Ren_Mor.U5_W3_D1.enums.StatoViaggio;
import Ren_Mor.U5_W3_D1.exceptions.ResourceNotFoundException;
import Ren_Mor.U5_W3_D1.payloads.ViaggioDTO;
import Ren_Mor.U5_W3_D1.repositories.DipendenteRepository;
import Ren_Mor.U5_W3_D1.repositories.ViaggioRepository;

import java.util.List;

@Service
@Slf4j
public class ViaggioService {

    @Autowired
    private ViaggioRepository viaggioRepository;

    @Autowired
    private DipendenteRepository dipendenteRepository;

    public Viaggio save(ViaggioDTO body) {
        Viaggio v = new Viaggio(body.destinazione(), body.data(), StatoViaggio.IN_PROGRAMMA);
        Viaggio saved = viaggioRepository.save(v);
        log.info("Viaggio con id: " + saved.getId() + " salvato correttamente!");
        return saved;
    }

    public List<Viaggio> getAll() {
        return viaggioRepository.findAll();
    }

    public Viaggio findById(int id) {
        return viaggioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Il viaggio non esiste"));
    }

    public Viaggio update(int id, ViaggioDTO body) {
        Viaggio found = findById(id);
        found.setDestinazione(body.destinazione());
        found.setDataPartenza(body.data());
        Viaggio modified = viaggioRepository.save(found);
        log.info("Viaggio con id: " + found.getId() + " modificato!");
        return modified;
    }

    public void delete(int id) {
        Viaggio found = findById(id);
        viaggioRepository.delete(found);
    }

    public Viaggio cambiaStato(int id, StatoViaggio stato) {
        Viaggio found = findById(id);
        found.setStato(stato);
        Viaggio modified = viaggioRepository.save(found);
        log.info("Stato viaggio con id: " + found.getId() + " modificato!");
        return modified;
    }

}