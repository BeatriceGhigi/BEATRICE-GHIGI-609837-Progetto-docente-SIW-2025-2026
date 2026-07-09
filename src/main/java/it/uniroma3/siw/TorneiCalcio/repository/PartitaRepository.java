package it.uniroma3.siw.TorneiCalcio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.uniroma3.siw.TorneiCalcio.model.Partita;
import it.uniroma3.siw.TorneiCalcio.model.Squadra;

public interface PartitaRepository extends JpaRepository<Partita, Long>{
	List<Partita> findBySquadraCasaOrSquadraOspite(Squadra squadraCasa, Squadra squadraOspite);
}
