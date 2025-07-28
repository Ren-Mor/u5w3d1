package Ren_Mor.U5_W3_D1.payloads;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record PrenotazioneDTO(
        @NotBlank(message = "Id del dipendente obbligatorio")
        Integer dipendenteId,
        @NotBlank(message = "Id del viaggio obbligatorio")
        Integer ViaggioId,
        @NotBlank(message = "Data richiesta obbligatoria")
        LocalDate dataRichiesta,
        String note) {
}
