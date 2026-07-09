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
			return "redirect:/giocatori";//metto a disposizione del componente che genera l'html, il componente che mette a disposizione l'html puù generare quest'oggetto
		}
	       model.addAttribute("giocatore", giocatore.get());                          //attraverso quetso nome qua
       return "giocatori/show";
	}
	
	
	
	@GetMapping("/giocatori")  /*questo metodo deve prendere tutti iflm e passarli al template */
	public String list(Model model) {
		List<Giocatore> giocatoreList= this.giocatoreService.findAll();
		model.addAttribute("giocatori", giocatoreList);
		return "giocatori/list";
	}
	
	@GetMapping("/giocatori/new") // metodo che ritorna la form
	public String createForm(Model model) {
		Giocatore giocatore= new Giocatore();
		model.addAttribute("giocatore", giocatore);
		model.addAttribute("ruoliDisponibili", Giocatore.Ruolo.values());
		model.addAttribute("squadre", squadraService.findAll());
		return "giocatori/form";
	}
	
	@PostMapping("/giocatori") // Salva i dati presi dalla form del giocatore
	public String save(@Valid @ModelAttribute("giocatore") Giocatore giocatore, 
	                   BindingResult bindingResult, 
	                   Model model) {

	    if (bindingResult.hasErrors()) {
	        model.addAttribute("ruoliDisponibili", Giocatore.Ruolo.values());
	        model.addAttribute("squadreTotali", this.squadraService.findAll());
	        return "giocatori/form"; 	    }
	    
	    this.giocatoreService.save(giocatore);
	    return "redirect:/giocatori";
	}
}
