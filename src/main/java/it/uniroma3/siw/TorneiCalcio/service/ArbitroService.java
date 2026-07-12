package it.uniroma3.siw.TorneiCalcio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.TorneiCalcio.model.Arbitro;
import it.uniroma3.siw.TorneiCalcio.repository.ArbitroRepository;
import jakarta.validation.Valid;


@Service
public class ArbitroService {

	private ArbitroRepository arbitroRepository;

	public ArbitroService(ArbitroRepository arbitroRepository) {
		this.arbitroRepository = arbitroRepository;
	}
	
	@Transactional(readOnly=true)
	public List<Arbitro> findAll(){
		List<Arbitro> arbitroList= (List<Arbitro>) this.arbitroRepository.findAll();
		return arbitroList;
	}
	
	@Transactional(readOnly=true)
	public Optional <Arbitro> findById(Long id) {
		return this.arbitroRepository.findById(id);	
	}

	public void save(@Valid Arbitro arbitro) {
		this.arbitroRepository.save(arbitro);
		
	}
}

