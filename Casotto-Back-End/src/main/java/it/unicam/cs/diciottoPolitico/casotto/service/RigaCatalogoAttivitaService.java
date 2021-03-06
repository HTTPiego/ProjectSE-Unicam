package it.unicam.cs.diciottoPolitico.casotto.service;

import it.unicam.cs.diciottoPolitico.casotto.model.SimpleRigaCatalogoAttivita;
import it.unicam.cs.diciottoPolitico.casotto.model.SimpleAttivita;
import it.unicam.cs.diciottoPolitico.casotto.model.SimpleRigaCatalogoOmbrellone;
import it.unicam.cs.diciottoPolitico.casotto.repository.RigaCatalogoAttivitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service del catalogo attivit&agrave;.
 * Esso si occupa di gestire le operazioni CRUD riguardanti la {@link SimpleRigaCatalogoAttivita} interagendo con il relativo
 * {@link RigaCatalogoAttivitaRepository}.
 *
 * @see SimpleRigaCatalogoAttivita
 * @see RigaCatalogoAttivitaRepository
 */
@Service
public class RigaCatalogoAttivitaService extends AbstractService<SimpleRigaCatalogoAttivita, RigaCatalogoAttivitaRepository> {

    @Autowired
    public RigaCatalogoAttivitaService(RigaCatalogoAttivitaRepository repository) {
        super(repository);
    }

    /**
     * Restituisce la lista di tutte le righe delle attivit&agrave; attualmente disponibili.
     *
     * @return la lista di tutte le righe delle attivit&agrave; attualmente disponibili
     */
    public List<SimpleRigaCatalogoAttivita> getAttivitaDisponibili() {
        return super.getBy(riga -> riga.getPostiTotali() > riga.getPostiOccupati());
    }

    /**
     * Restituisce la lista di tutte le righe delle attivit&agrave; attualmente non disponibili.
     *
     * @return la lista di tutte le righe delle attivit&agrave; attualmente non disponibili
     */
    public List<SimpleRigaCatalogoAttivita> getAttivitaNonDisponibili() {
        return super.getBy(riga -> riga.getPostiTotali() == riga.getPostiOccupati());
    }

    /**
     * Restituisce la lista di tutte le righe delle attivit&agrave; aventi prezzo minore o uguale al prezzo specificato.
     *
     * @param prezzoLimite il prezzo limite incluso
     * @return la lista di tutte le righe delle attivit&agrave; aventi prezzo minore o uguale al prezzo specificato
     */
    public List<SimpleRigaCatalogoAttivita> filtraBy(double prezzoLimite) {
        return super.getBy(riga -> riga.getPrezzo() <= prezzoLimite);
    }

    /**
     * Restituisce la lista di tutte le righe delle attivit&agrave; aventi come numero di posti totali minore o uguale al numero di posto specificato.
     *
     * @param numeroMaxPostiTotali il numero di posti totali limite incluso
     * @return la lista di tutte le righe delle attivit&agrave; aventi prezzo minore o uguale al prezzo specificato
     */
    public List<SimpleRigaCatalogoAttivita> filtraBy(int numeroMaxPostiTotali) {
        return super.getBy(riga -> riga.getPostiTotali() <= numeroMaxPostiTotali);
    }

    /**
     * Restituisce un {@link Optional} che descrive una {@link SimpleRigaCatalogoAttivita} avente come nome della {@link SimpleAttivita} il nome specificato
     * oppure un empty {@code Optional} se non viene trovata nessuna riga avente il nome dell' attivit&agrave; specificato nel database.
     *
     * @param nomeAttivita il nome dell' attivit&agrave; di cui ricavare la riga
     * @return un {@code Optional} che descrive una {@code SimpleRigaCatalogoBar} avente il nome dell' attivit&agrave; specificato
     * oppure un empty {@code Optional} se non viene trovata nessuna riga con il nome dell' attivit&agrave; specificato
     */
    public Optional<SimpleRigaCatalogoAttivita> getRigaBy(String nomeAttivita) {
        return super.getBy(riga -> riga.getValore().getNome().equals(nomeAttivita)).stream().findFirst();
    }

    @Override
    public SimpleRigaCatalogoAttivita save(SimpleRigaCatalogoAttivita rigaCatalogo){
        var foundriga =
                super.getAll().stream().filter(riga->riga.equals(rigaCatalogo)).findFirst();
        if (foundriga.isEmpty()
                && this.dateCorrette(rigaCatalogo)
                && rigaCatalogo.getPostiOccupati()<=rigaCatalogo.getPostiTotali()){
            return super.save(rigaCatalogo);
        }
        return null;
    }

    private boolean dateCorrette(SimpleRigaCatalogoAttivita rigaCatalogo){
        return rigaCatalogo.getValore().getDataOrarioFine().isAfter(rigaCatalogo.getValore().getDataOrarioInizio())
                || rigaCatalogo.getValore().getDataOrarioInizio().equals(rigaCatalogo.getValore().getDataOrarioFine());
    }

    public SimpleRigaCatalogoAttivita update(SimpleRigaCatalogoAttivita rigaCatalogoAttivita){
        var foundriga =
                super.getAll().stream().filter(riga->riga.getId().equals(rigaCatalogoAttivita.getId())).findFirst();
        if (foundriga.isPresent()
                && this.dateCorrette(rigaCatalogoAttivita)
                && rigaCatalogoAttivita.getPostiOccupati()<=rigaCatalogoAttivita.getPostiTotali()){
            return super.save(rigaCatalogoAttivita);
        }
        return null;
    }

        public Optional<SimpleRigaCatalogoAttivita> getRigaBy(SimpleAttivita riga){
        return super.getBy(r -> r.getValore().equals(riga))
                .stream()
                .findFirst();
    }

}
