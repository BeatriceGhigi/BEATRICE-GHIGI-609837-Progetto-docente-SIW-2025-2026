package it.uniroma3.siw.TorneiCalcio.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import it.uniroma3.siw.TorneiCalcio.model.Arbitro;


public interface ArbitroRepository extends JpaRepository<Arbitro,Long>{

	 Arbitro findByCodiceArbitrale(Integer codiceArbitrale);
	
}
