package it.uniroma3.siw.TorneiCalcio.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.TorneiCalcio.model.Utente;
import it.uniroma3.siw.TorneiCalcio.repository.UtenteRepository;


@Service
public class UtenteService {

	private UtenteRepository utenteRepository;

	public UtenteService(UtenteRepository utenteRepository) {
		this.utenteRepository = utenteRepository;
	}
	
	
	@Transactional(readOnly = true)
	public Utente getUser(Long id) {
		return this.utenteRepository.findById(id).orElse(null);
	}

	
	@Transactional
	public Utente saveUser(Utente user) {
		return this.utenteRepository.save(user);
	}
}
