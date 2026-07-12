package it.uniroma3.siw.TorneiCalcio.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.TorneiCalcio.model.ClassificaDto;
import it.uniroma3.siw.TorneiCalcio.model.Partita;
import it.uniroma3.siw.TorneiCalcio.model.Partita.StatusPartita;
import it.uniroma3.siw.TorneiCalcio.model.Squadra;
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
	public Optional<Torneo> findById(Long id) {
		return this.torneoRepository.findById(id);
	}
	
@Transactional
	public void save(Torneo torneo) {  
		this.torneoRepository.save(torneo);  
		
	}

@Transactional(readOnly=true)
public List<ClassificaDto> generaClassifica(Long torneoId) {
	// 1. Cerchiamo il torneo (se non c'è, restituiamo una lista vuota o errore)
	Optional<Torneo> torneoOpt = this.torneoRepository.findById(torneoId);
	if (!torneoOpt.isPresent()) {
		return new ArrayList<>();
	}
	Torneo torneo = torneoOpt.get();

	List<ClassificaDto> classifica = new ArrayList<>();

	// 2. Per ogni squadra del torneo, creiamo una riga della classifica inizializzata a 0
	for (Squadra squadra : torneo.getSquadre()) {
		ClassificaDto riga = new ClassificaDto(squadra.getNome(), 0, 0, 0, 0);
		classifica.add(riga);
	}

	// 3. Scorriamo le partite per calcolare i punti
	for (Partita partita : torneo.getPartite()) {
		// Consideriamo solo le partite giocate/terminate
		if (partita.getStato() == StatusPartita.PLAYED) { 
			
			// Cerchiamo i DTO delle due squadre coinvolte dentro la nostra lista della classifica
			ClassificaDto datiCasa = null;
			ClassificaDto datiOspite = null;

			for (ClassificaDto r : classifica) {
				if (r.getNomeSquadra().equals(partita.getSquadraCasa().getNome())) {
					datiCasa = r;
				}
				if (r.getNomeSquadra().equals(partita.getSquadraOspite().getNome())) {
					datiOspite = r;
				}
			}

			// Se abbiamo trovato le squadre, aggiorniamo i loro dati
			if (datiCasa != null && datiOspite != null) {
				datiCasa.setPartiteGiocate(datiCasa.getPartiteGiocate() + 1);
				datiOspite.setPartiteGiocate(datiOspite.getPartiteGiocate() + 1);

				// Controllo del risultato
				if (partita.getGoalsHome() > partita.getGoalsAway()) {
					// Vince Casa
					datiCasa.setVittorie(datiCasa.getVittorie() + 1);
					datiCasa.setPunti(datiCasa.getPunti() + 3);
					datiOspite.setSconfitte(datiOspite.getSconfitte() + 1);
				} 
				else if (partita.getGoalsAway() > partita.getGoalsHome()) {
					// Vince Ospite
					datiOspite.setVittorie(datiOspite.getVittorie() + 1);
					datiOspite.setPunti(datiOspite.getPunti() + 3);
					datiCasa.setSconfitte(datiCasa.getSconfitte() + 1);
				} 
				else {
					// Pareggio
					datiCasa.setPunti(datiCasa.getPunti() + 1);
					datiOspite.setPunti(datiOspite.getPunti() + 1);
				}
			}
		}
	}

	// 4. Ordiniamo la classifica per punti (dal più alto al più basso) con un ordinamento classico
	classifica.sort(new Comparator<ClassificaDto>() {
		@Override
		public int compare(ClassificaDto o1, ClassificaDto o2) {
			return Integer.compare(o2.getPunti(), o1.getPunti()); // Decrescente
		}
	});

	return classifica;
}
}
