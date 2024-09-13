package diegobasili.gestioneViaggiAziendali.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record PrenotazioneDTO(@NotNull
                              LocalDate dataRichiesta,
                              @Size(min = 0, max = 250)
                              String note,
                              @NotNull
                              UUID dipendeteID,
                              @NotNull
                              UUID viaggioId) {
}
