package it.unicam.cs.diciottoPolitico.casotto.service;

import it.unicam.cs.diciottoPolitico.casotto.entity.*;
import it.unicam.cs.diciottoPolitico.casotto.entity.implementation.SimplePrenotazioneOmbrellone;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Semplice implementazione dell'interfaccia HandlerPrenotazioneOmbrellone.
 */
public class SimpleHandlerPrenotazioneOmbrellone implements HandlerPrenotazioneOmbrellone {

    private final Catalogo<Ombrellone, RigaCatalogoOmbrellone> catalogoOmbrelloni;
    private final List<PrenotazioneOmbrellone> prenotazioniOmbrelloni;

    /**
     * Metodo Costruttore.
     */
    public SimpleHandlerPrenotazioneOmbrellone() {
        this.catalogoOmbrelloni = new SimpleCatalogo<>();
        this.prenotazioniOmbrelloni = new ArrayList<>();
    }

    @Override
    public List<RigaCatalogoOmbrellone> getRigheCatalogoBy(GregorianCalendar data, FasciaOraria fasciaOraria) {
        return null;/*this.catalogoOmbrelloni.getAllRighe().stream()
                .filter(riga -> riga.getDisponibilita(Objects.requireNonNull(data,"La data non puo' essere nulla"), Objects.requireNonNull(fasciaOraria,"La fascia oraria non puo' essere nulla")))
                .collect(Collectors.toList());*/
    }

    //todo prenotazione salvata doppiamente sia su catalogo che sull'handler, riga 40
    @Override
    public boolean creaPrenotazione(GregorianCalendar data, FasciaOraria fasciaOraria, RigaCatalogoOmbrellone rigaCatalogoOmbrellone, Utente utente) {
        /*if(rigaCatalogoOmbrellone == null || data == null || fasciaOraria==null)
            throw new NullPointerException("Nessun parametro puo' essere nullo");
        if(this.catalogoOmbrelloni.getAllRighe().contains(rigaCatalogoOmbrellone)) {
            if (rigaCatalogoOmbrellone.getDisponibilita(data, fasciaOraria)) {
                PrenotazioneOmbrellone prenotazioneOmbrellone = new SimplePrenotazioneOmbrellone(fasciaOraria, rigaCatalogoOmbrellone.getValore(), data, rigaCatalogoOmbrellone.getPrezzoOmbrellone(), utente);
                rigaCatalogoOmbrellone.addPrenotazione(prenotazioneOmbrellone);
                return this.prenotazioniOmbrelloni.add(prenotazioneOmbrellone);
            }
        }*/
        return false;
    }

    @Override
    public String getRiepilogo(GregorianCalendar data, FasciaOraria fasciaOraria, Ombrellone ombrellone) {
        return data.getTime() + "\n" + fasciaOraria.toString() + "\n" + ombrellone.getId();
    }

    @Override
    public List<PrenotazioneOmbrellone> getPrenotazioniOmbrellone() {
        return this.prenotazioniOmbrelloni;
    }

    @Override
    public boolean removePrenotazioneOmbrellone(PrenotazioneOmbrellone prenotazioneOmbrellone) {
        Objects.requireNonNull(prenotazioneOmbrellone,"La prenotazione ombrellone non puo' essere nulla");
        return this.prenotazioniOmbrelloni.removeIf(prenotazione -> prenotazione.equals(prenotazioneOmbrellone));
    }
}