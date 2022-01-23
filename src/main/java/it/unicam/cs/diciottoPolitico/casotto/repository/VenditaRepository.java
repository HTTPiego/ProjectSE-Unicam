package it.unicam.cs.diciottoPolitico.casotto.repository;

import it.unicam.cs.diciottoPolitico.casotto.entity.implementation.SimpleVendita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VenditaRepository extends JpaRepository<SimpleVendita, UUID> {
}