package it.uniroma3.siw.TorneiCalcio.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.uniroma3.siw.TorneiCalcio.model.Squadra;
import it.uniroma3.siw.TorneiCalcio.repository.SquadraRepository;

@Service
public class SquadraService {
	
	private SquadraRepository squadraRepository;

	public SquadraService(SquadraRepository squadraRepository) {
		super();
		this.squadraRepository = squadraRepository;
	}
	
	public List<Squadra> findAll() {
		List<Squadra> squadraList= (List<Squadra>) this.squadraRepository.findAll();
		return squadraList;
	}

}
