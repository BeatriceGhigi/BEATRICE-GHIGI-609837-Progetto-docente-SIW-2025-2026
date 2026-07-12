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

import it.uniroma3.siw.TorneiCalcio.model.Partita;
import it.uniroma3.siw.TorneiCalcio.service.ArbitroService;
import it.uniroma3.siw.TorneiCalcio.service.PartitaService;
import it.uniroma3.siw.TorneiCalcio.service.SquadraService;
import jakarta.validation.Valid;

@Controller
public class PartitaController {

	private ArbitroService arbitroService;
	private PartitaService partitaService;
	private SquadraService squadraService;

	public PartitaController(PartitaService partitaService, SquadraService squadraService, ArbitroService arbitroService) {
		this.partitaService = partitaService;
		this.squadraService= squadraService;
		this.arbitroService=arbitroService;
	}

	@GetMapping("/partite/{id}")
	public String show(@PathVariable("id") Long id, Model model) {

		Optional<Partita> partita= this.partitaService.findById(id);
		if(partita.isEmpty()) {
			return "redirect:/partite";
		}
		model.addAttribute("partita", partita.get());                      
		return "partite/show";
	}

	@GetMapping("/partite")  
	public String list(Model model) {
		List<Partita> partitaList= this.partitaService.findAll();
		model.addAttribute("partite",partitaList);
		return "partite/list";
	}

	@GetMapping("/admin/partite")  
	public String adminList(Model model) {
		List<Partita> partitaList= this.partitaService.findAll();
		model.addAttribute("partite",partitaList);
		return "admin/partite/list-admin";
	}


	@GetMapping("/admin/partite/new")
	public String createForm(Model model) {
		Partita partita= new Partita();
		model.addAttribute("statiDisponibili", Partita.StatusPartita.values());
		model.addAttribute("partita", partita);
		model.addAttribute("squadre", squadraService.findAll());
		model.addAttribute("arbitri",this.arbitroService.findAll()); 
		return "admin/partite/form";
	}

	@PostMapping("/admin/partite") 
	public String save(@Valid @ModelAttribute("partita") Partita partita, 
			BindingResult bindingResult, 
			Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("statiDisponibili", Partita.StatusPartita.values());
			model.addAttribute("squadre", this.squadraService.findAll());
			return "admin/partite/form"; 	    }

		this.partitaService.save(partita);
		return "redirect:/partite";

	}

	@PostMapping("/admin/partite/{id}/delete")
	public String delete(@PathVariable Long id) {
		partitaService.deleteById(id);
		return "redirect:/admin/partite";
	}

	@GetMapping("/admin/partite/{id}/edit")
	public String editForm(@PathVariable Long id, Model model) {
		Optional<Partita> optional = partitaService.findById(id);
		if (optional.isEmpty()) {
			return "redirect:/partite";
		}
		Partita partita = optional.get();
		model.addAttribute("partita", partita);
		model.addAttribute("statiDisponibili", Partita.StatusPartita.values()); 
		model.addAttribute("squadre", squadraService.findAll());    
		model.addAttribute("arbitri", this.arbitroService.findAll());
		return "admin/partite/form";
	}
}
