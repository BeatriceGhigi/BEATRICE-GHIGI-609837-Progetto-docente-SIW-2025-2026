package it.uniroma3.siw.TorneiCalcio.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.TorneiCalcio.model.Commento;
import it.uniroma3.siw.TorneiCalcio.model.Partita;
import it.uniroma3.siw.TorneiCalcio.model.Utente;
import it.uniroma3.siw.TorneiCalcio.service.CommentoService;
import it.uniroma3.siw.TorneiCalcio.service.PartitaService;
import it.uniroma3.siw.TorneiCalcio.service.UtenteService;
import jakarta.validation.Valid;

@Controller
public class CommentoController {

	private final CommentoService commentoService;
	private final PartitaService partitaService;
	private final UtenteService utenteService;

	public CommentoController(CommentoService commentoService, PartitaService partitaService, UtenteService utenteService) {
		this.commentoService = commentoService;
		this.partitaService = partitaService;
		this.utenteService = utenteService;
	}

	// Form commento
	@GetMapping("/partite/{partitaId}/commenti/new")
	public String createCommentoForm(@PathVariable("partitaId") Long partitaId, Model model) {
		Optional<Partita> partitaOpt = this.partitaService.findById(partitaId);
		if (partitaOpt.isEmpty()) {
			return "redirect:/partite";
		}

		model.addAttribute("partita", partitaOpt.get());
		model.addAttribute("commento", new Commento()); // Passiamo un oggetto commento vuoto alla form
		return "commenti/form";
	}

	// Salvataggio commento
	@PostMapping("/partite/{partitaId}/commenti")
	public String addCommento(@PathVariable("partitaId") Long partitaId, 
			@Valid @ModelAttribute("commento") Commento nuovoCommento,
			BindingResult bindingResult, 
			Principal principal,
			Model model) {

		Optional<Partita> partitaOpt = this.partitaService.findById(partitaId);
		if (partitaOpt.isEmpty() || principal == null) {
			return "redirect:/partite";
		}

		//errori
		if (bindingResult.hasErrors()) {
			model.addAttribute("partita", partitaOpt.get());
			return "commenti/form";
		}

		// Recupera l'utente correntemente loggato nel sistema
		Utente utenteLoggato = this.utenteService.findByUsername(principal.getName());

		nuovoCommento.setPartita(partitaOpt.get());
		nuovoCommento.setAutore(utenteLoggato); 

		this.commentoService.save(nuovoCommento);
		return "redirect:/partite/" + partitaId;
	}


	//Form di modifica
	@GetMapping("/commenti/{id}/edit")
	public String editCommentoForm(@PathVariable Long id, Model model, Principal principal) {
		Optional<Commento> commentoOpt = this.commentoService.findById(id);
		if (commentoOpt.isEmpty() || principal == null) {
			return "redirect:/partite";
		}

		Commento commento = commentoOpt.get();
		// Controllo di Sicurezza
		if (!commento.getAutore().getUsername().equals(principal.getName())) {
			return "redirect:/partite/" + commento.getPartita().getId(); 
		}

		model.addAttribute("commento", commento);
		model.addAttribute("partita", commento.getPartita()); 

		return "commenti/form";
	}

	// Salvataggio modifiche
	@PostMapping("/commenti/{id}/edit")
	public String updateCommento(@PathVariable Long id, 
			@Valid @ModelAttribute("commento") Commento commentoModificato, 
			BindingResult bindingResult,
			Principal principal,
			Model model) {

		Optional<Commento> commentoOpt = this.commentoService.findById(id);
		if (commentoOpt.isEmpty() || principal == null) {
			return "redirect:/partite";
		}

		Commento commentoDb = commentoOpt.get();
		if (!commentoDb.getAutore().getUsername().equals(principal.getName())) {
			return "redirect:/partite/" + commentoDb.getPartita().getId();
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("partita", commentoDb.getPartita());
			return "commenti/form";
		}


		commentoDb.setTitolo(commentoModificato.getTitolo());
		commentoDb.setTesto(commentoModificato.getTesto());

		this.commentoService.save(commentoDb);

		return "redirect:/partite/" + commentoDb.getPartita().getId();
	} 
}