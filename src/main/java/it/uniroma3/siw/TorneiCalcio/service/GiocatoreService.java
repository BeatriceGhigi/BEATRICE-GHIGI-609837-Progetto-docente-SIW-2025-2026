package it.uniroma3.siw.TorneiCalcio.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.uniroma3.siw.TorneiCalcio.model.Giocatore;
import it.uniroma3.siw.TorneiCalcio.repository.GiocatoreRepository;

@Service
public class GiocatoreService {

	private GiocatoreRepository giocatoreRepository;

	public GiocatoreService(GiocatoreRepository giocatoreRepository) {
		this.giocatoreRepository = giocatoreRepository;
	}
	
	public List<Giocatore> findAll(){

		List<Giocatore> giocatoreList=(List<Giocatore>) this.giocatoreRepository.findAll();
		return giocatoreList;
		
	}
	
}
