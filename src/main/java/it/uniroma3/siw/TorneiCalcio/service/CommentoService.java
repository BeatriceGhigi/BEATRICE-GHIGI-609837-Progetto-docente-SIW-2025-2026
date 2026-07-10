package it.uniroma3.siw.TorneiCalcio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.TorneiCalcio.model.Commento;
import it.uniroma3.siw.TorneiCalcio.repository.CommentoRepository;

@Service
public class CommentoService {

	private CommentoRepository commentoRepository;

	public CommentoService(CommentoRepository commentoRepository) {
		this.commentoRepository = commentoRepository;
	}
	
	@Transactional(readOnly=true)
	public List<Commento> findAll(){

		List<Commento> commentoList=(List<Commento>) this.commentoRepository.findAll();
		return commentoList;
		
	}
	
	@Transactional(readOnly=true)
	public Optional<Commento> findById(Long id) {
		return this.commentoRepository.findById(id);	
	}
	
	@Transactional
	public void save(Commento commento) {  //mi serve per poter salvare i dati presi dall form
	 	this.commentoRepository.save(commento);  // associato al metodo save in controller
		
	}
	
}
