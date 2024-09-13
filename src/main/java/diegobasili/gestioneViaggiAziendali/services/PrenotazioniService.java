package diegobasili.gestioneViaggiAziendali.services;

import diegobasili.gestioneViaggiAziendali.entities.Dipendente;
import diegobasili.gestioneViaggiAziendali.entities.Prenotazione;
import diegobasili.gestioneViaggiAziendali.entities.Viaggio;
import diegobasili.gestioneViaggiAziendali.exceptions.BadRequestException;
import diegobasili.gestioneViaggiAziendali.exceptions.NotFoundException;
import diegobasili.gestioneViaggiAziendali.payloads.DipendenteDTO;
import diegobasili.gestioneViaggiAziendali.payloads.PrenotazioneDTO;
import diegobasili.gestioneViaggiAziendali.payloads.ViaggioDTO;
import diegobasili.gestioneViaggiAziendali.repositories.DipendentiRepository;
import diegobasili.gestioneViaggiAziendali.repositories.PrenotazioniRepository;
import diegobasili.gestioneViaggiAziendali.repositories.ViaggiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PrenotazioniService {
    @Autowired
    private PrenotazioniRepository prenotazioniRepository;
    @Autowired
    private DipendentiRepository dipendentiRepository;
    @Autowired
    private ViaggiRepository viaggiRepository;

    public Page<Prenotazione> findAll(int page, int size, String sortBy) {
        if (page>20) page=20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.prenotazioniRepository.findAll(pageable);
    }

    public Prenotazione savePrenotazione(PrenotazioneDTO body) {
        Dipendente dipendente = dipendentiRepository.findById(body.dipendeteID()).orElseThrow(()-> new NotFoundException(body.dipendeteID()));
        Viaggio viaggio = viaggiRepository.findById(body.viaggioId()).orElseThrow(()-> new NotFoundException(body.viaggioId()));
        List<Prenotazione> listaPrenotazioni = prenotazioniRepository.findByDipendenteAndViaggioData(dipendente, viaggio.getData());
        if (!listaPrenotazioni.isEmpty()) {
            throw new BadRequestException("esiste gia una prenotazione in questa data: " + viaggio.getData() + " per questo dipendente: " + dipendente.getCognome());
        }
        Prenotazione prenotazione = new Prenotazione(body.dataRichiesta(), body.note(), dipendente, viaggio);
        return this.prenotazioniRepository.save(prenotazione);
    }

    public Prenotazione findById(UUID prenotazioneId) {
        return this.prenotazioniRepository.findById(prenotazioneId).orElseThrow(()-> new NotFoundException(prenotazioneId));
    }

    public Prenotazione findByIdAndUpdate(UUID prenotazioneId, PrenotazioneDTO updateBody) {
        Dipendente dipendente = dipendentiRepository.findById(updateBody.dipendeteID()).orElseThrow(()-> new NotFoundException(updateBody.dipendeteID()));
        Viaggio viaggio = viaggiRepository.findById(updateBody.viaggioId()).orElseThrow(()-> new NotFoundException(updateBody.viaggioId()));
        Prenotazione found = findById(prenotazioneId);
        found.setNote(updateBody.note());
        found.setDipendente(dipendente);
        found.setViaggio(viaggio);
        found.setData_richiesta(updateBody.dataRichiesta());
        return found;
    }

    public void findByIdAndDelete(UUID prenotazioneId) {
        Prenotazione found = findById(prenotazioneId);
        this.prenotazioniRepository.delete(found);
    }
}
