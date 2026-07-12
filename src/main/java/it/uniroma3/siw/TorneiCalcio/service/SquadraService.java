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
	public Squadra save(Squadra squadra) {  
		return this.squadraRepository.save(squadra);  

	}

	@Transactional
	public void deleteById(Long id) {
		Optional<Squadra> optional = this.squadraRepository.findById(id);
		if (optional.isPresent()) {
			Squadra squadra = optional.get();


			if (squadra.getTornei() != null) {
				for (Torneo torneo : squadra.getTornei()) {
					torneo.getSquadre().remove(squadra);


				}
			}


			if (this.partitaService != null) {
				List<Partita> partiteCollegate = this.partitaService.findBySquadra(squadra);
				for (Partita p : partiteCollegate) {
					this.partitaService.deleteById(p.getId());
				}
			}


			this.squadraRepository.delete(squadra);
		}
	}

}
