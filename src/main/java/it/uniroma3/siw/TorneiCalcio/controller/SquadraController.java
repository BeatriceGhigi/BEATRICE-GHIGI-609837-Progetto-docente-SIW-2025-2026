package it.uniroma3.siw.TorneiCalcio.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.TorneiCalcio.model.Giocatore;
import it.uniroma3.siw.TorneiCalcio.model.Squadra;
import it.uniroma3.siw.TorneiCalcio.service.GiocatoreService;
import it.uniroma3.siw.TorneiCalcio.service.PartitaService;
import it.uniroma3.siw.TorneiCalcio.service.SquadraService;
import jakarta.validation.Valid;

@Controller
public class SquadraController {

	private PartitaService partitaService;
	private GiocatoreService giocatoreService;
	private SquadraService squadraService;

	public SquadraController(SquadraService squadraService, GiocatoreService giocatoreService, PartitaService partitaService) {
		this.squadraService = squadraService;
		this.giocatoreService= giocatoreService;
		this.partitaService= partitaService;
	}
	
	@GetMapping("/squadre/{id}")
	public String show(@PathVariable("id") Long id, Model model) {
		
		Optional<Squadra> squadra= this.squadraService.findById(id);
		if(squadra.isEmpty()) {
			return "redirect:/squadre";
		}
		model.addAttribute("squadra", squadra.get()); 
		model.addAttribute("partite", partitaService.findBySquadra(squadra.get()));//metto a disposizione del componente che genera l'html, il componente che mette a disposizione l'html puù generare quest'oggetto
	    return "squadre/show";               //attraverso quetso nome qua
        
	}
	
	@GetMapping("/squadre")  /*questo metodo deve prendere tutti iflm e passarli al template */
	public String list(Model model) {
		List<Squadra> squadraList= this.squadraService.findAll();
		model.addAttribute("squadre",squadraList);
		return "squadre/list";
	}
	
	@GetMapping("/admin/squadre/new") // metodo che ritorna la form
	public String createForm(Model model) {
		Squadra squadra = new Squadra();
		model.addAttribute("squadra", squadra);
		model.addAttribute("giocatori", giocatoreService.findAll());
		return "admin/squadre/form";
	}

	@PostMapping("/admin/squadre")  //mi salva i dati presi dalla form
	public String save(@Valid @ModelAttribute("squadra") Squadra squadra, 
			BindingResult bindingResult, Model model,
	        @RequestParam(required = false) String action,
	        @RequestParam(required = false) Long nuovoGiocatoreId,
	        @RequestParam(required = false) List<Long> giocatoriIds) {

	    // Ricostruisce la lista squadre dagli hidden input
	    List<Giocatore> giocatori = new ArrayList<>();
	    if (giocatoriIds != null) {
	        for (Long id : giocatoriIds) {
	            Optional<Giocatore> optional = giocatoreService.findById(id);
	            if (optional.isPresent()) {
	                giocatori.add(optional.get());
	            }
	        }
	    }
	    squadra.setGiocatori(giocatori);
	    if ("addGiocatore".equals(action)) {
	        if (nuovoGiocatoreId != null && nuovoGiocatoreId > 0) {
	            Optional<Giocatore> giocatore = giocatoreService.findById(nuovoGiocatoreId);
	            if (giocatore.isPresent() && !squadra.getGiocatori().contains(giocatore.get())) {
	                squadra.getGiocatori().add(giocatore.get());
	            }
	        }
	        model.addAttribute("giocatori", giocatoreService.findAll());
	        return "admin/squadre/form";
	    }

	    if (bindingResult.hasErrors()) {
	        model.addAttribute("giocatori", giocatoreService.findAll());
	        return "admin/squadre/form";
	    }

	    this.squadraService.save(squadra);
	    return "redirect:/squadre";
}
		
		@PostMapping("/admin/squadre/{id}/delete")
		public String delete(@PathVariable Long id) {
			squadraService.deleteById(id);
			return "redirect:/squadre";
		}
		
		@GetMapping("/admin/squadre/{id}/edit")
	    public String editForm(@PathVariable Long id, Model model) {
	        Optional<Squadra> optional = squadraService.findById(id);
	        if (optional.isEmpty()) {
	            return "redirect:/squadre";
	        }
	        Squadra squadra = optional.get();
	        if (squadra.getGiocatori() == null) squadra.setGiocatori(new ArrayList<>());
	        model.addAttribute("squadra", squadra);
	        model.addAttribute("giocatori", giocatoreService.findAll());
	        return "admin/squadre/form";
	    }
	}

