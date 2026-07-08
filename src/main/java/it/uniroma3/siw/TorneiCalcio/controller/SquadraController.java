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

import it.uniroma3.siw.TorneiCalcio.model.Squadra;
import it.uniroma3.siw.TorneiCalcio.service.SquadraService;
import jakarta.validation.Valid;

@Controller
public class SquadraController {

	private SquadraService squadraService;

	public SquadraController(SquadraService squadraService) {
		this.squadraService = squadraService;
	}
	
	@GetMapping("/squadre/{id}")
	public String show(@PathVariable("id") Long id, Model model) {
		
		Optional<Squadra> squadra= this.squadraService.findById(id);
		model.addAttribute("squadra", squadra); //metto a disposizione del componente che genera l'html, il componente che mette a disposizione l'html puù generare quest'oggetto
	                                       //attraverso quetso nome qua
       return "squadre/show";
	}
	
	@GetMapping("/squadre")  /*questo metodo deve prendere tutti iflm e passarli al template */
	public String list(Model model) {
		List<Squadra> squadraList= this.squadraService.findAll();
		model.addAttribute("squadre",squadraList);
		return "squadre/list";
	}
	
	@GetMapping("/squadre/new") // metodo che ritorna la form
	public String form(Model model) {
		model.addAttribute("squadra", new Squadra());
		return "squadre/form";
	}

	@PostMapping("/squadre")  //mi salva i dati presi dalla form
	public String save(@Valid @ModelAttribute("squadra") Squadra squadra, BindingResult bindingResult) {
		
			if(bindingResult.hasErrors()) {  //riscontra i singoli errori della form
	              return "/squadre/form";
			}
			else {
				this.squadraService.save(squadra);
			return "redirect:/squadre";
		
	}
	
	}
}
