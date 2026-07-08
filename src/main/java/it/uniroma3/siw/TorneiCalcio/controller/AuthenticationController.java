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
	private final UtenteService utenteService;
	private final PasswordEncoder passwordEncoder; 

	public AuthenticationController(UtenteService utenteService, PasswordEncoder passwordEncoder) {
	
		this.utenteService = utenteService;
		this.passwordEncoder = passwordEncoder;
	}


	@GetMapping(value = "/register") 
	public String showRegisterForm(Model model) {
		model.addAttribute("utente", new Utente());
		return "authentication/registerUser";
	}
	
	
	@GetMapping(value = "/login") 
	public String showLoginForm(Model model) {
		return "authentication/login";
	}

	@GetMapping(value = "/admin/index")
	public String index() {
		return "admin/index";
	}
		
	@PostMapping(value = { "/register" })
	public String registerUser(@Valid @ModelAttribute("utente") Utente utente,
			BindingResult utenteBindingResult, Model model) {

		// 1. Controlla se ci sono errori di validazione (es. campi vuoti)
		if (utenteBindingResult.hasErrors()) {
			return "authentication/registerUser";
		}

		// 2. Assegna il ruolo USER di default a chi si registra autonomamente
		utente.setRuolo(Utente.USER_ROLE);

		// 3. CRUCIALE: Cripta la password prima di salvarla nel database!
		// Se la salvi in chiaro, Spring Security non permetterà il login.
		String passwordCriptata = this.passwordEncoder.encode(utente.getPassword());
		utente.setPassword(passwordCriptata);

		// 4. Salva direttamente l'utente tramite l'utenteService
		this.utenteService.saveUser(utente); 

		return "redirect:/login"; // Dopo la registrazione, lo mandiamo al login
}
}
