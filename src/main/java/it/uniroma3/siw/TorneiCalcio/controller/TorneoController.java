package it.uniroma3.siw.TorneiCalcio.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.TorneiCalcio.model.Torneo;
import it.uniroma3.siw.TorneiCalcio.service.TorneoService;




@Controller
public class TorneoController {
	
	private TorneoService torneoService;

	public TorneoController (TorneoService torneoService) { //costruttore
		  this.torneoService=torneoService;	
		}
	

	@GetMapping("/tornei/{id}")   /*questa richiesta è associata ad un metodo get fatto in questo modo */
    public String show(@PathVariable("id") Long id, Model model) {
	
		Torneo torneo= this.torneoService.findeById(id);
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
}
