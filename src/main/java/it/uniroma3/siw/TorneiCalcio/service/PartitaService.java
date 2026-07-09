package it.uniroma3.siw.TorneiCalcio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.TorneiCalcio.model.Partita;
import it.uniroma3.siw.TorneiCalcio.model.Squadra;
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
	public Optional<Partita> findById(Long id) {
		return this.partitaRepository.findById(id);	
	}
	
	@Transactional(readOnly=true)
	public List<Partita> findBySquadra(Squadra squadra) {
        return partitaRepository.findBySquadraCasaOrSquadraOspite(squadra, squadra);
    }

	@Transactional
	public void save(Partita partita) {  //mi serve per poter salvare i dati presi dall form
		this.partitaRepository.save(partita);  // associato al metodo save in controller
		
	}
	
	@Transactional
	public void deleteById(Long id) {
		partitaRepository.deleteById(id);
	}
}
