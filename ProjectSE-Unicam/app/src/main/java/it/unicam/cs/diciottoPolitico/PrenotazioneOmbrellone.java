package it.unicam.cs.diciottoPolitico;

import java.util.GregorianCalendar;

/**
 * Interfaccia che descrive una prenotazione di un ombrellone.
 *
 * @see Ombrellone
 * @see FasciaOraria
 * @see Categoria
 */
public interface PrenotazioneOmbrellone {

    /**
     * Ottieni il codice associato all'ombrellone.
     *
     * @return il codice dell'ombrellone
     */
    long getId();

    /**
     * Ottieni la data in cui &egrave; riservata la prenotazione.
     *
     * @return la data di prenotazione
     */
    GregorianCalendar getDataPrenotazione();

    /**
     * Ottieni la da in cui la prenotazione &egrave; stata acquistata.
     *
     * @return la data di acquisto della prenotazione
     */
    GregorianCalendar getDataAcquisto();

    /**
     * Ottieni lo stato di pagamento della prenotazione.
     *
     * @return {@code true} se la prenotazione &grave; stata gi&agrave; saldata,
     * {@code false} altrimenti
     */
    boolean getStatoPagamento();

    /**
     * Ottieni il costo della prenotazione.
     *
     * @return il costo della prenotazione
     */
    double getCosto();

    /**
     * Ottieni l' {@link Ombrellone} associato alla prenotazione
     *
     * @return l'ombrellone della prenotazione
     */
    Ombrellone getOmbrellone();

    /**
     * Ottieni la {@link FasciaOraria} nella quale la prenotazione &egrave; riservata.
     *
     * @return la fascia oraria della prenotazione
     */
    FasciaOraria getFasciaOraria();

    /**
     * Ritorna l'utente che ha effettuato la prenotazione.
     *
     * @return l'utente che ha effettuato la prenotazione
     */
    Utente getUtente();
}
