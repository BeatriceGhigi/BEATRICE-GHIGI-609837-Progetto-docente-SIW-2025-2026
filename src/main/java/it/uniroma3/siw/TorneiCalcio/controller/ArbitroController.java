package it.uniroma3.siw.TorneiCalcio.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.TorneiCalcio.model.Arbitro;
import it.uniroma3.siw.TorneiCalcio.service.ArbitroService;
import jakarta.validation.Valid;

@Controller
public class ArbitroController {

	private ArbitroService arbitroService;

	
	public ArbitroController(ArbitroService arbitroService) {
		this.arbitroService = arbitroService;
	}
	
	// 1. Mostra i dettagli di un singolo arbitro (Pubblico)
	@GetMapping("/arbitri/{id}")
	public String show(@PathVariable("id") Long id, Model model) {
		Optional<Arbitro> arbitro = this.arbitroService.findById(id);
		if(arbitro.isEmpty()) {
			return "redirect:/arbitri";
		}
		model.addAttribute("arbitro", arbitro.get());
		return "arbitri/show";
	}
	
	// 2. Lista di tutti gli arbitri (Pubblico)
	@GetMapping("/arbitri")
	public String list(Model model) {
		List<Arbitro> arbitriList = this.arbitroService.findAll();
		model.addAttribute("arbitri", arbitriList);
		return "arbitri/list";
	}
	
	// 3. Lista degli arbitri per l'Amministratore
	@GetMapping("/admin/arbitri")
	public String adminList(Model model) {
		List<Arbitro> arbitriList = this.arbitroService.findAll();
		model.addAttribute("arbitri", arbitriList);
		return "admin/arbitri/list-admin";
	}
	

	// 4. Form di creazione nuovo arbitro (Solo Admin)
	@GetMapping("/admin/arbitri/new")
	public String createForm(Model model) {
		Arbitro arbitro = new Arbitro();
		model.addAttribute("arbitro", arbitro);
		return "admin/arbitri/form";
	}
	
	// 5. Salvataggio dell'arbitro (Solo Admin)
	@PostMapping("/admin/arbitri")
	public String save(@Valid @ModelAttribute("arbitro") Arbitro arbitro, 
	                   BindingResult bindingResult, 
	                   Model model) {

		if (bindingResult.hasErrors()) {
			// Se ci sono errori (es. nome troppo corto o vuoto), ricarica il form
			return "admin/arbitri/form"; 
		}
		
		this.arbitroService.save(arbitro);
		return "redirect:/arbitri"; // Scegli tu se reindirizzare alla lista pubblica o admin
	}
	
	
	
	// 7. Form di modifica arbitro (Solo Admin)
	@GetMapping("/admin/arbitri/{id}/edit")
	public String editForm(@PathVariable Long id, Model model) {
		Optional<Arbitro> optional = this.arbitroService.findById(id);
		if (optional.isEmpty()) {
			return "redirect:/admin/arbitri";
		}
		Arbitro arbitro = optional.get();
		model.addAttribute("arbitro", arbitro);         
		return "admin/arbitri/form";
	}
}