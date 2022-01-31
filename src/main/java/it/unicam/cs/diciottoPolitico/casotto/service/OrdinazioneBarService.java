package it.unicam.cs.diciottoPolitico.casotto.service;

import it.unicam.cs.diciottoPolitico.casotto.model.*;
import it.unicam.cs.diciottoPolitico.casotto.repository.OrdinazioneBarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service delle ordinazioni bar.
 * Esso si occupa di gestire le operazioni CRUD riguardanti la {@link SimpleOrdinazioneBar} interagendo con il relativo
 * {@link OrdinazioneBarRepository}.
 *
 * @see SimpleOrdinazioneBar
 * @see OrdinazioneBarRepository
 */
@Service
public class OrdinazioneBarService extends AbstractService<SimpleOrdinazioneBar, OrdinazioneBarRepository> {

    private final RigaCatalogoOmbrelloneService ombrelloneService;
    private final RigaCatalogoBarService barService;
    private final NotificaService notificaService;
    private final UtenteService utenteService;

    /**
     * Crea un service per le ordinazioni iniettando il repository degli articoli bar, i service delle notifiche e degli ombrelloni e il repository delle ordinazioni bar specificati.
     *
     * @param repository        il repository delle ordinazioni bar da iniettare
     * @param notificaService   il service delle notifiche da iniettare
     * @param ombrelloneService il service degli ombrelloni da iniettare
     * @param barService        il service degli articoli bar da iniettare
     */
    @Autowired
    public OrdinazioneBarService(OrdinazioneBarRepository repository, NotificaService notificaService, RigaCatalogoOmbrelloneService ombrelloneService, RigaCatalogoBarService barService, UtenteService utenteService) {
        super(repository);
        this.notificaService = notificaService;
        this.ombrelloneService = ombrelloneService;
        this.barService = barService;
        this.utenteService = utenteService;
    }

    public List<SimpleRigaCatalogoBar> getArticoliBarDisponibili() {
        return this.barService.getRigheDisponibili();
    }

    /**
     * Restituisce la lista di tutte le ordinazioni effettuate dai clienti che si trovano nello {@link StatusOrdinazioneBar} specificato.
     *
     * @param status lo status in cui si devono trovare le ordinazioni
     * @return la lista di tutte le ordinazioni che si trovano nello {@code StatusOrdinazioneBar} specificato
     */
    public List<SimpleOrdinazioneBar> filtraBy(StatusOrdinazioneBar status) {
        return super.getBy(o -> o.getStatusOrdinazioneBar().equals(status));
    }

    /**
     * Restituisce la lista di tutte le ordinazioni effettuate dai clienti per il {@link SimpleArticoloBar} specificato.
     *
     * @param articoloBar l' articolo bar che devono avere le ordinazioni
     * @return la lista di tutte le ordinazioni che riguardano il {@code SimpleArticoloBar}
     */
    public List<SimpleOrdinazioneBar> filtraBy(SimpleArticoloBar articoloBar) {
        return super.getBy(o -> o.getArticoloBar().equals(articoloBar));
    }

    /**
     * Restituisce la lista delle ordinazioni effettuate dai clienti per il nome specificato del {@link SimpleArticoloBar}.
     *
     * @param nomeArticoloBar il nome dell' articolo bar del {@code SimpleArticoloBar} della {@link SimpleOrdinazioneBar}
     * @return la lista di tutte le ordinazioni che riguardano il nome del {@code SimpleArticoloBar}
     */
    public List<SimpleOrdinazioneBar> filtraBy(String nomeArticoloBar) {
        return super.getBy(o -> o.getArticoloBar().getNome().equals(nomeArticoloBar));
    }

    /**
     * Esegue un controllo sulla presenza del {@link SimpleArticoloBar} nella {@link SimpleOrdinazioneBar} specificata.
     * Restituisce un empty {@link Optional} se non viene trovato nessun {@code SimpleArticoloBar} specificato nel database, altrimenti memorizza l' ordinazione
     * specificata nel database e restituisce un {@code Optional} che descrive la {@code SimpleOrdinazioneBar} memorizzata.
     *
     * @param ordinazione l' ordinazione di cui eseguire il controllo e memorizzarla nel database
     * @return un empty {@link Optional} se non viene trovato nessun articolo della {@code SimpleOrdinazioneBar} specificata nel database
     * oppure se non viene trovato nessun {@code QRCode} con il nome specificato nel database, altrimenti memorizza l' ordinazione
     * specificata nel database e restituisce un {@code Optional} che descrive la {@code SimpleOrdinazioneBar} memorizzata.
     */
    public Optional<SimpleOrdinazioneBar> checkAndSave(SimpleOrdinazioneBar ordinazione) {
        SimpleNotifica notifica = new SimpleNotifica();
        var a = this.checkArticolo(ordinazione.getArticoloBar().getId());
        if (a.isEmpty())
            return Optional.empty();
        notifica.setMessaggio("Arrivata ordinazione con id: " + ordinazione.getId());
        notifica.setUtenti(this.utenteService.filtraBy(RuoloUtente.ADDETTO_BAR));
        this.notificaService.inviaNotifica(notifica);
        var r = this.barService.getRigaBy(a.get()).get();
        r.setQuantita(r.getQuantita() - 1);
        this.barService.checkAndUpdate(r);
        return Optional.of(super.save(ordinazione));
    }

    private Optional<SimpleArticoloBar> checkArticolo(UUID articoloBarId) {
        return this.barService.getRigaBy(articoloBarId).map(SimpleRigaCatalogoBar::getValore);
    }

}
