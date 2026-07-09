package it.uniroma3.siw.TorneiCalcio.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.TorneiCalcio.model.Utente;
import it.uniroma3.siw.TorneiCalcio.service.UtenteService;
import jakarta.validation.Valid;

@Controller
public class AuthenticationController {
	
	private  final PasswordEncoder passwordEncoder;
	private final UtenteService utenteService;


	public AuthenticationController(PasswordEncoder passwordEncoder, UtenteService utenteService) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.utenteService = utenteService;
	}

	@GetMapping(value = "/register") 
	public String showRegisterForm (Model model) {
		model.addAttribute("utente", new Utente());
		return "authentication/registerUser";
	}
	
	@GetMapping(value = "/login") 
	public String showLoginForm (Model model) {
		return "authentication/login";
	}

	@GetMapping(value = "/admin/index")
	public String index() {
		return "admin/index";
	}
		
	@PostMapping(value = { "/register" })
    public String registerUser(@Valid @ModelAttribute("utente") Utente utente,
                 BindingResult utenteBindingResult) { 
		if (utenteBindingResult.hasErrors()) {
			return "authentication/registerUser";
		}
		utente.setRuolo(Utente.DEFAULT_ROLE);
		utente.setPassword(this.passwordEncoder.encode(utente.getPassword()));
		this.utenteService.save(utente);
		return "redirect:/";
		
                     }
}