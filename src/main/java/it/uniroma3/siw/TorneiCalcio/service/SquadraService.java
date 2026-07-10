package it.uniroma3.siw.TorneiCalcio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.TorneiCalcio.model.Partita;
import it.uniroma3.siw.TorneiCalcio.model.Squadra;
import it.uniroma3.siw.TorneiCalcio.model.Torneo;
import it.uniroma3.siw.TorneiCalcio.repository.SquadraRepository;


@Service
public class SquadraService {
	
	private PartitaService partitaService;
	private SquadraRepository squadraRepository;

	public SquadraService(SquadraRepository squadraRepository, PartitaService partitaService) {
		this.squadraRepository = squadraRepository;
		this.partitaService=partitaService;
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
	public Squadra save(Squadra squadra) {  //mi serve per poter salvare i dati presi dall form
		return this.squadraRepository.save(squadra);  // associato al metodo save in controller
		
	}
	
	@Transactional
	public void deleteById(Long id) {
	    Optional<Squadra> optional = this.squadraRepository.findById(id);
	    if (optional.isPresent()) {
	        Squadra squadra = optional.get();
	        
	        // 1. Rimuove la squadra dai tornei per pulire la tabella torneo_squadra
	        if (squadra.getTornei() != null) {
	            for (Torneo torneo : squadra.getTornei()) {
	                torneo.getSquadre().remove(squadra);
	                // Se hai un torneoRepository/Service iniettato puoi salvarlo, 
	                // altrimenti l'orphan removal o la cascata sul ManyToMany farà il suo corso,
	                // ma rimuoverlo esplicitamente a livello di entità previene il blocco del DB.
	            }
	        }
	        
	        // 2. Trova ed elimina tutte le partite in cui questa squadra ha giocato (Fix precedente)
	        if (this.partitaService != null) {
	            List<Partita> partiteCollegate = this.partitaService.findBySquadra(squadra);
	            for (Partita p : partiteCollegate) {
	                this.partitaService.deleteById(p.getId());
	            }
	        }
	        
	        // 3. Ora la squadra è completamente slegata e puoi eliminarla in sicurezza
	        this.squadraRepository.delete(squadra);
	    }
	}
	
}
