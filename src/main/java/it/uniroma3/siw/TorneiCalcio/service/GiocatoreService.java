package it.uniroma3.siw.TorneiCalcio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.TorneiCalcio.model.Giocatore;

import it.uniroma3.siw.TorneiCalcio.repository.GiocatoreRepository;


@Service
public class GiocatoreService {

	private GiocatoreRepository giocatoreRepository;

	public GiocatoreService(GiocatoreRepository giocatoreRepository) {
		this.giocatoreRepository = giocatoreRepository;
	}

	@Transactional(readOnly=true)
	public List<Giocatore> findAll(){

		List<Giocatore> giocatoreList=(List<Giocatore>) this.giocatoreRepository.findAll();
		return giocatoreList;

	}

	@Transactional(readOnly=true)
	public Optional<Giocatore> findById(Long id) {
		return this.giocatoreRepository.findById(id);	
	}

	@Transactional
	public Giocatore save(Giocatore giocatore) {  
		return 	this.giocatoreRepository.save(giocatore);  

	}

}
