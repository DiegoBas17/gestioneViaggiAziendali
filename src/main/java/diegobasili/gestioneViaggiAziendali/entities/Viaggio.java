package diegobasili.gestioneViaggiAziendali.entities;

import diegobasili.gestioneViaggiAziendali.enums.StatoViaggio;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Viaggio {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue
    private UUID id;
    private String destinazione;
    private LocalDate data;
    private StatoViaggio stato_viaggio;
}
