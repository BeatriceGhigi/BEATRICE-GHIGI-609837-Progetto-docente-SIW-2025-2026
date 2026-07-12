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


import it.uniroma3.siw.TorneiCalcio.model.Giocatore;
import it.uniroma3.siw.TorneiCalcio.service.GiocatoreService;
import it.uniroma3.siw.TorneiCalcio.service.SquadraService;
import jakarta.validation.Valid;

@Controller
public class GiocatoreController {

	private SquadraService squadraService;
	private GiocatoreService giocatoreService;

	public GiocatoreController(GiocatoreService giocatoreService, SquadraService squadraService) {
		this.giocatoreService = giocatoreService;
		this.squadraService= squadraService;
	}

	@GetMapping("/giocatori/{id}")
	public String show(@PathVariable("id") Long id, Model model) {
		Optional<Giocatore> giocatore= this.giocatoreService.findById(id);
		if(giocatore.isEmpty()) {
			return "redirect:/giocatori";
		}
		model.addAttribute("giocatore", giocatore.get());           
		return "giocatori/show";
	}



	@GetMapping("/admin/giocatori") 
	public String list(Model model) {
		List<Giocatore> giocatoreList= this.giocatoreService.findAll();
		model.addAttribute("giocatori", giocatoreList);
		return "admin/giocatori/list";
	}

	@GetMapping("/admin/giocatori/new") 
	public String createForm(Model model) {
		Giocatore giocatore= new Giocatore();
		model.addAttribute("giocatore", giocatore);
		model.addAttribute("ruoliDisponibili", Giocatore.Ruolo.values());
		model.addAttribute("squadre", squadraService.findAll());
		return "admin/giocatori/form";
	}

	@PostMapping("/admin/giocatori") 
	public String save(@Valid @ModelAttribute("giocatore") Giocatore giocatore, 
			BindingResult bindingResult, 
			Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("ruoliDisponibili", Giocatore.Ruolo.values());
			model.addAttribute("squadreTotali", this.squadraService.findAll());
			return "admin/giocatori/form"; 	    }

		this.giocatoreService.save(giocatore);
		return "redirect:/admin/giocatori";
	}

	@GetMapping("/admin/giocatori/{id}/edit")
	public String editForm(@PathVariable Long id, Model model) {
		Optional<Giocatore> optional = giocatoreService.findById(id);
		if (optional.isEmpty()) {
			return "redirect:/admin/giocatori";
		}
		Giocatore giocatore = optional.get();
		model.addAttribute("giocatore", giocatore);
		model.addAttribute("ruoliDisponibili", Giocatore.Ruolo.values()); 
		model.addAttribute("squadre", squadraService.findAll());          
		return "admin/giocatori/form";
	}
}
