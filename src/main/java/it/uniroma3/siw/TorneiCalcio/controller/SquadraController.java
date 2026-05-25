package it.uniroma3.siw.TorneiCalcio.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.TorneiCalcio.model.Squadra;
import it.uniroma3.siw.TorneiCalcio.service.SquadraService;

@Controller
public class SquadraController {

	private SquadraService squadraService;

	public SquadraController(SquadraService squadraService) {
		this.squadraService = squadraService;
	}
	
	@GetMapping("/squadre/{id}")
	public String show(@PathVariable("id") Long id, Model model) {
		
		Squadra squadra= this.squadraService.findById(id);
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
}
