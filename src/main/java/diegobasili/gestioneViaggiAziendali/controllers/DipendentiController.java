package diegobasili.gestioneViaggiAziendali.controllers;

import diegobasili.gestioneViaggiAziendali.entities.Dipendente;
import diegobasili.gestioneViaggiAziendali.payloads.DipendenteDTO;
import diegobasili.gestioneViaggiAziendali.services.DipendentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/dipendenti")
public class DipendentiController {
    @Autowired
    private DipendentiService dipendentiService;

    @GetMapping
    public Page<Dipendente> findAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "id") String sortBy){
        return this.dipendentiService.findAll(page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente save(@RequestBody DipendenteDTO body){
        return this.dipendentiService.saveDipendente(body);
    }

    @GetMapping("/{dipendenteId}")
    public Dipendente findById(@PathVariable UUID dipendenteId){
        return this.dipendentiService.findById(dipendenteId);
    }

    @PutMapping("/{dipendenteId}")
    public Dipendente findByIdAndUpdate(@PathVariable UUID dipendenteId, @RequestBody DipendenteDTO body){
        return this.dipendentiService.findByIdAndUpdate(dipendenteId, body);
    }

    @DeleteMapping("/{dipendenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID dipendenteId){
        this.dipendentiService.findByIdAndDelete(dipendenteId);
    }
}
