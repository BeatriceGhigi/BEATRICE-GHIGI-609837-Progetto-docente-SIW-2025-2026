package it.uniroma3.siw.TorneiCalcio.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.TorneiCalcio.model.Torneo;
import it.uniroma3.siw.TorneiCalcio.repository.TorneoRepository;


@Service
public class TorneoService {
	private TorneoRepository torneoRepository;

	public TorneoService(TorneoRepository torneoRepository) {
		this.torneoRepository = torneoRepository;
	}
	
	@Transactional(readOnly=true)
	public List<Torneo> findAll() {
		List<Torneo> torneoList= (List<Torneo>) this.torneoRepository.findAll();
		return torneoList;
	}

	@Transactional(readOnly=true)
	public Torneo findById(Long id) {
		return this.torneoRepository.findById(id).get();
	}
	
	
 
}
