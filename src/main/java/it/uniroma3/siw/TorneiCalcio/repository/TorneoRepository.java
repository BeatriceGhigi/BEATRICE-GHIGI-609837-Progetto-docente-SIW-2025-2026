package it.uniroma3.siw.TorneiCalcio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import it.uniroma3.siw.TorneiCalcio.model.Torneo;

public interface TorneoRepository extends JpaRepository<Torneo, Long>{

}
