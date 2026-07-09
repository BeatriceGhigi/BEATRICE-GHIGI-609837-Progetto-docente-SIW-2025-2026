package it.uniroma3.siw.TorneiCalcio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.TorneiCalcio.model.Squadra;
import it.uniroma3.siw.TorneiCalcio.repository.SquadraRepository;


@Service
public class SquadraService {
	
	private SquadraRepository squadraRepository;

	public SquadraService(SquadraRepository squadraRepository) {
		this.squadraRepository = squadraRepository;
	}
	
	@Transactional(readOnly=true)
	public List<Squadra> findAll() {
		List<Squadra> squadraList= (List<Squadra>) this.squadraRepository.findAll();
		return squadraList;
	}

	@Transactional(readOnly=true)
	public Optional<Squadra> findById(Long id) {
		return this.squadraRepository.findById(id);	
	}
	
	@Transactional
	public void save(Squadra squadra) {  //mi serve per poter salvare i dati presi dall form
		this.squadraRepository.save(squadra);  // associato al metodo save in controller
		
	}
	
	@Transactional
	public void deleteById(Long id) {
		squadraRepository.deleteById(id);
	}
	
}
