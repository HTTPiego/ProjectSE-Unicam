package it.unicam.cs.diciottoPolitico.casotto.service;

import it.unicam.cs.diciottoPolitico.casotto.model.SimpleNotifica;
import it.unicam.cs.diciottoPolitico.casotto.model.SimpleUtente;
import it.unicam.cs.diciottoPolitico.casotto.repository.NotificaRepository;
import it.unicam.cs.diciottoPolitico.casotto.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class NotificaService extends AbstractService<SimpleNotifica, NotificaRepository> {

    private final UtenteRepository utenteRepository;

    @Autowired
    public NotificaService(NotificaRepository notificaRepository, UtenteRepository utenteRepository) {
        super(notificaRepository);
        this.utenteRepository = utenteRepository;
    }

    public void inviaNotifica(SimpleNotifica notifica, Set<SimpleUtente> utenti) {
        utenti.forEach(u -> notifica.getUtenti().add(u));
        this.save(notifica);
    }

    private List<UUID> getUUIDs(List<SimpleUtente> utentiDestinatari) {
        return utentiDestinatari.stream().map(SimpleUtente::getId).collect(Collectors.toList());

    }

    public void rimuoviNotifica(SimpleNotifica notifica) {
        //this.notificaRepository.delete(notifica);
        this.utenteRepository.
                findAllById(this.getUUIDs(new ArrayList<>(notifica.getUtenti()))).
                forEach(utente -> {
                    utente.getNotifiche().remove(notifica);
                    this.utenteRepository.save(utente);
                });
    }

}