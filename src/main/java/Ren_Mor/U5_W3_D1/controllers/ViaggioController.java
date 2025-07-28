package Ren_Mor.U5_W3_D1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import Ren_Mor.U5_W3_D1.entities.Viaggio;
import Ren_Mor.U5_W3_D1.enums.StatoViaggio;
import Ren_Mor.U5_W3_D1.exceptions.ValidationException;
import Ren_Mor.U5_W3_D1.payloads.ViaggioDTO;
import Ren_Mor.U5_W3_D1.services.ViaggioService;

import java.util.List;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {

    @Autowired
    private ViaggioService viaggioService;

    @GetMapping
    public List<Viaggio> findAll() {
        return viaggioService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio save(@RequestBody @Validated ViaggioDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors()
                    .stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        }
        return viaggioService.save(payload);
    }

    @GetMapping("/{id}")
    public Viaggio getById(@PathVariable int id) {
        return viaggioService.findById(id);
    }

    @PutMapping("/{id}")
    public Viaggio update(@PathVariable int id, @RequestBody @Validated ViaggioDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors()
                    .stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        }
        return viaggioService.update(id, payload);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        viaggioService.delete(id);
    }

    @PatchMapping("/{id}/stato")
    public Viaggio cambiaStato(@PathVariable int id, @RequestParam StatoViaggio stato) {
        return viaggioService.cambiaStato(id, stato);
    }
}
