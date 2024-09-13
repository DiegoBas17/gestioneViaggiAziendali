package diegobasili.gestioneViaggiAziendali.services;

import diegobasili.gestioneViaggiAziendali.repositories.PrenotazioniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioniService {
    @Autowired
    private PrenotazioniRepository prenotazioniRepository;


}
