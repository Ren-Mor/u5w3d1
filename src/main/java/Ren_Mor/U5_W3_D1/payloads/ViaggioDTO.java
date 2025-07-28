package Ren_Mor.U5_W3_D1.payloads;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record ViaggioDTO(
        @NotBlank(message = "Destinazione obbligatoria")
        String destinazione,
        @NotBlank(message = "Data obbligatoria")
        LocalDate data) {
}
