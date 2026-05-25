package it.uniroma3.siw.TorneiCalcio.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.TorneiCalcio.model.Partita;
import it.uniroma3.siw.TorneiCalcio.repository.PartitaRepository;



@Service
public class PartitaService {
	
	private PartitaRepository partitaRepository;

	public PartitaService(PartitaRepository partitaRepository) {
		this.partitaRepository = partitaRepository;
	}
	
	@Transactional(readOnly=true)
	public List<Partita> findAll(){

		List<Partita> partitaList=(List<Partita>) this.partitaRepository.findAll();
		return partitaList;
		
	}	

	@Transactional(readOnly=true)
	public Partita findeById(Long id) {
		return this.partitaRepository.findById(id).get();	
	}
}
