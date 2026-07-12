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

import it.uniroma3.siw.TorneiCalcio.model.Squadra;
import it.uniroma3.siw.TorneiCalcio.model.Torneo;
import it.uniroma3.siw.TorneiCalcio.service.SquadraService;
import it.uniroma3.siw.TorneiCalcio.service.TorneoService;
import jakarta.validation.Valid;

@Controller
public class TorneoController {

	private SquadraService squadraService;
	private TorneoService torneoService;

	public TorneoController(TorneoService torneoService,SquadraService squadraService) {
		this.torneoService = torneoService;
		this.squadraService= squadraService;
	}



	@GetMapping("/tornei/{id}")
	public String show(@PathVariable("id") Long id, Model model) {

		Optional<Torneo> torneo= this.torneoService.findById(id);
		if(torneo.isEmpty()) {
			return "redirect:/tornei";
		}
		model.addAttribute("torneo", torneo.get());   
		return "tornei/show";
	}

	@GetMapping("/tornei")  
	public String list(Model model) {
		List<Torneo> torneoList= this.torneoService.findAll();
		model.addAttribute("tornei",torneoList);
		return "tornei/list";
	}

	@GetMapping("/admin/tornei")  
	public String adminList(Model model) {
		List<Torneo> torneoList= this.torneoService.findAll();
		model.addAttribute("tornei",torneoList);
		return "admin/tornei/list-admin";
	}

	@GetMapping("/admin/tornei/new") 
	public String createForm(Model model) {
		Torneo torneo= new Torneo();
		torneo.setSquadre(new ArrayList<>());
		model.addAttribute("torneo", torneo);
		model.addAttribute("squadre", squadraService.findAll());
		return "admin/tornei/form";
	}

	@PostMapping("/admin/tornei")  
	public String save(@Valid @ModelAttribute("torneo") Torneo torneo, 
			BindingResult bindingResult, Model model,
			@RequestParam(required = false) String action,
			@RequestParam(required = false) Long nuovaSquadraId,
			@RequestParam(required = false) List<Long> squadreIds) {


		List<Squadra> squadre = new ArrayList<>();
		if (squadreIds != null) {
			for (Long id : squadreIds) {
				Optional<Squadra> optional = squadraService.findById(id);
				if (optional.isPresent()) {
					squadre.add(optional.get());
				}
			}
		}
		torneo.setSquadre(squadre);
		if ("addSquadra".equals(action)) {
			if (nuovaSquadraId != null && nuovaSquadraId > 0) {
				Optional<Squadra> squadra = squadraService.findById(nuovaSquadraId);
				if (squadra.isPresent() && !torneo.getSquadre().contains(squadra.get())) {
					torneo.getSquadre().add(squadra.get());
				}
			}
			model.addAttribute("squadre", squadraService.findAll());
			return "admin/tornei/form";
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("squadre", squadraService.findAll());
			return "admin/tornei/form";
		}

		this.torneoService.save(torneo);
		return "redirect:/tornei";
	}
	@GetMapping("/admin/tornei/{id}/edit")
	public String editForm(@PathVariable Long id, Model model) {
		Optional<Torneo> optional = torneoService.findById(id);
		if (optional.isEmpty()) {
			return "redirect:/admin/tornei";
		}
		Torneo torneo = optional.get();
		if (torneo.getSquadre() == null) torneo.setSquadre(new ArrayList<>());
		model.addAttribute("torneo", torneo);
		model.addAttribute("squadre", squadraService.findAll());
		return "admin/tornei/form";
	}


}

