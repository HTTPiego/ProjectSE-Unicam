package it.unicam.cs.diciottoPolitico.casotto.repository;

import it.unicam.cs.diciottoPolitico.casotto.model.AreaInfrastruttura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Interfaccia per le operazioni CRUD sul repository dell' {@link AreaInfrastruttura}.
 */
public interface InfrastrutturaRepository extends JpaRepository<AreaInfrastruttura, UUID> {
}
