package it.uniroma3.siw.TorneiCalcio.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.TorneiCalcio.model.Giocatore;
import it.uniroma3.siw.TorneiCalcio.service.GiocatoreService;

@Controller
public class GiocatoreController {

	private GiocatoreService giocatoreService;

	public GiocatoreController(GiocatoreService giocatoreService) {
		this.giocatoreService = giocatoreService;
	}
	
	@GetMapping("/giocatori/{id}")
	public String show(@PathVariable("id") Long id, Model model) {
		
		Giocatore giocatore= this.giocatoreService.findById(id);
		model.addAttribute("giocatore", giocatore); //metto a disposizione del componente che genera l'html, il componente che mette a disposizione l'html puù generare quest'oggetto
	                                       //attraverso quetso nome qua
       return "giocatore/show";
	}
	
	@GetMapping("/giocatori")  /*questo metodo deve prendere tutti iflm e passarli al template */
	public String list(Model model) {
		List<Giocatore> giocatoreList= this.giocatoreService.findAll();
		model.addAttribute("giocatori", giocatoreList);
		return "giocatori/list";
	}

	
}
