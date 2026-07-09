package it.uniroma3.siw.TorneiCalcio.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalController {

    @ModelAttribute("userDetails")
    public UserDetails getUtente() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        // Controllo di sicurezza: verifichiamo che l'autenticazione esista, sia valida e non anonima
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            Object principal = authentication.getPrincipal();
            
            // CRITICO: Eseguiamo il cast a UserDetails SOLO se il principal è effettivamente un'istanza corretta
            if (principal instanceof UserDetails) {
                return (UserDetails) principal;
            }
        }
        
        // Se non c'è nessuno loggato o la sessione è invalida, restituisce null senza spaccare la pagina
        return null;
    }
}