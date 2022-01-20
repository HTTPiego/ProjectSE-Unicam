package it.unicam.cs.diciottoPolitico.casotto.entity;

import java.util.GregorianCalendar;
import java.util.UUID;

/**
 * Interfaccia che descrive una generica Vendita
 */
public interface Vendita {

    /**
     * Ottieni il codice univoco associato alla vendita
     * @return il codice identificativo della vendita
     */
    UUID getCodice();

    /**
     * Ottieni la data in cui &grave; stata effettuata la vendita
     * @return la data di acquisto della vendita
     */
    GregorianCalendar getDataAcquisto();

    /**
     * Ottieni il costo associato ad una vendita
     * @return il costo della prenotazione
     */
    double getCosto();

    /**
     * Ottieni lo stato di pagamento della vendita
     * @return {@code true} se la vendita &grave; stata saldata,
     *         {@code false} altrimenti
     */
    boolean isPagata();

    /**
     * Imposta lo stato del pagamento della vendita secondo quanto descritto
     * dal {@param stataPagamento}
     * @param statoPagamento della vendita
     */
    void setIsPagata(boolean statoPagamento);
}