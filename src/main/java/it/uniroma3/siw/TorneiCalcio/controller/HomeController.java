package it.uniroma3.siw.TorneiCalcio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class HomeController {

	@GetMapping("/")  /*quando arriva / ti dico che templste gestisce*/
	public String getHome() {
		return("index");
	}
	
}
