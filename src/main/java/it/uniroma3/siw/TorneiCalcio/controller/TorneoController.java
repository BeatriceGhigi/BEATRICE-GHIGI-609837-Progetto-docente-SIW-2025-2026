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


import it.uniroma3.siw.TorneiCalcio.model.Torneo;
import it.uniroma3.siw.TorneiCalcio.service.TorneoService;
import jakarta.validation.Valid;

@Controller
public class TorneoController {

	private TorneoService torneoService;

	public TorneoController(TorneoService torneoService) {
		this.torneoService = torneoService;
	}
	
	@GetMapping("/tornei/{id}")
	public String show(@PathVariable("id") Long id, Model model) {
		
		Optional<Torneo> torneo= this.torneoService.findById(id);
		model.addAttribute("torneo", torneo); //metto a disposizione del componente che genera l'html, il componente che mette a disposizione l'html puù generare quest'oggetto
	                                       //attraverso quetso nome qua
       return "tornei/show";
	}
	
	@GetMapping("/tornei")  /*questo metodo deve prendere tutti iflm e passarli al template */
	public String list(Model model) {
		List<Torneo> torneoList= this.torneoService.findAll();
		model.addAttribute("tornei",torneoList);
		return "tornei/list";
	}
	
	@GetMapping("/tornei/new") // metodo che ritorna la form
	public String form(Model model) {
		model.addAttribute("torneo", new Torneo());
		return "tornei/form";
	}

	@PostMapping("/tornei")  //mi salva i dati presi dalla form
	public String save(@Valid @ModelAttribute("torneo") Torneo torneo, BindingResult bindingResult) {
		
			if(bindingResult.hasErrors()) {  //riscontra i singoli errori della form
	              return "tornei/form";
			}
			else {
				this.torneoService.save(torneo);
			return "redirect:/tornei";
		
	}
	
	}
}
