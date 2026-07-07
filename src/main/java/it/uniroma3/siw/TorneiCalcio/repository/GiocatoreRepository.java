package it.uniroma3.siw.TorneiCalcio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import it.uniroma3.siw.TorneiCalcio.model.Giocatore;

public interface GiocatoreRepository extends JpaRepository<Giocatore, Long> {

}
