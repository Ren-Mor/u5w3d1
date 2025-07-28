package Ren_Mor.U5_W3_D1.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import Ren_Mor.U5_W3_D1.entities.Dipendente;
import Ren_Mor.U5_W3_D1.exceptions.BadRequestException;
import Ren_Mor.U5_W3_D1.exceptions.ResourceNotFoundException;
import Ren_Mor.U5_W3_D1.payloads.DipendenteDTO;
import Ren_Mor.U5_W3_D1.repositories.DipendenteRepository;


import java.util.List;

@Service
@Slf4j
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;




    public Dipendente save(DipendenteDTO body) {
        dipendenteRepository.findByEmail(body.email()).ifPresent(d -> {
            throw new BadRequestException("Email già in uso");
        });
        Dipendente d = new Dipendente(body.cognome(), body.nome(), body.username(), body.email());
        d.setImmagineProfilo("https://" + body.nome() + "+" + body.cognome());
        Dipendente saved = dipendenteRepository.save(d);
        log.info("Dipendente con id: " + saved.getId() + " salvato correttamente!");
        return saved;
    }

    public List<Dipendente> getAll() {
        return dipendenteRepository.findAll();
    }

    public Dipendente findById(int id) {
        return dipendenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Il dipendente non esiste"));
    }

    public Dipendente update(int id, DipendenteDTO body) {
        Dipendente found = findById(id);
        if (!found.getEmail().equals(body.email()))
            dipendenteRepository.findByEmail(body.email()).ifPresent(d -> {
                throw new BadRequestException("Email già in uso");
            });
        found.setCognome(body.cognome());
        found.setNome(body.nome());
        found.setUsername(body.username());
        found.setEmail(body.email());
        found.setImmagineProfilo("https://ui-avatars.com/api/?name=" + body.nome() + "+" + body.cognome());
        Dipendente modified = dipendenteRepository.save(found);
        log.info("Dipendente con id: " + found.getId() + " modificato!");
        return modified;
    }

    public void delete(int id) {
        Dipendente found = findById(id);
        dipendenteRepository.delete(found);
    }
}