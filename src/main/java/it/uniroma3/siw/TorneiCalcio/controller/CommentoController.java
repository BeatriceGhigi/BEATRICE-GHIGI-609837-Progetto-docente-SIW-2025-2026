package it.uniroma3.siw.TorneiCalcio.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.TorneiCalcio.model.Commento;
import it.uniroma3.siw.TorneiCalcio.model.Partita;
import it.uniroma3.siw.TorneiCalcio.model.Utente;
import it.uniroma3.siw.TorneiCalcio.service.CommentoService;
import it.uniroma3.siw.TorneiCalcio.service.PartitaService;
import it.uniroma3.siw.TorneiCalcio.service.UtenteService;
import jakarta.validation.Valid;

@Controller
public class CommentoController {

    private final CommentoService commentoService;
    private final PartitaService partitaService;
    private final UtenteService utenteService;

    public CommentoController(CommentoService commentoService, PartitaService partitaService, UtenteService utenteService) {
        this.commentoService = commentoService;
        this.partitaService = partitaService;
        this.utenteService = utenteService;
    }

    // 1. FORM DI INSERIMENTO (GET): Mostra la pagina per aggiungere un commento legato a una partita
    @GetMapping("/partite/{partitaId}/commenti/new")
    public String createCommentoForm(@PathVariable("partitaId") Long partitaId, Model model) {
        Optional<Partita> partitaOpt = this.partitaService.findById(partitaId);
        if (partitaOpt.isEmpty()) {
            return "redirect:/partite";
        }
        
        model.addAttribute("partita", partitaOpt.get());
        model.addAttribute("commento", new Commento()); // Passiamo un oggetto commento vuoto alla form
        return "commenti/form";
    }

    // 2. SALVATAGGIO INSERIMENTO (POST): Riceve i dati (titolo e testo) della form, convalida e salva
    @PostMapping("/partite/{partitaId}/commenti")
    public String addCommento(@PathVariable("partitaId") Long partitaId, 
                              @Valid @ModelAttribute("commento") Commento nuovoCommento,
                              BindingResult bindingResult, 
                              Principal principal,
                              Model model) {
        
        Optional<Partita> partitaOpt = this.partitaService.findById(partitaId);
        if (partitaOpt.isEmpty() || principal == null) {
            return "redirect:/partite";
        }

        // Se ci sono errori di validazione (es. titolo o testo vuoti), ricarica la form
        if (bindingResult.hasErrors()) {
            model.addAttribute("partita", partitaOpt.get());
            return "commenti/form";
        }

        // Recupera l'utente correntemente loggato nel sistema
        Utente utenteLoggato = this.utenteService.findByUsername(principal.getName());

        // Iniettiamo le relazioni mancanti che non passano dalla form HTML
        nuovoCommento.setPartita(partitaOpt.get());
        nuovoCommento.setAutore(utenteLoggato); 

        this.commentoService.save(nuovoCommento);
        return "redirect:/partite/" + partitaId;
    }


 // 3. FORM DI MODIFICA (GET): Mostra la pagina per editare titolo e testo del commento
    @GetMapping("/commenti/{id}/edit")
    public String editCommentoForm(@PathVariable Long id, Model model, Principal principal) {
        Optional<Commento> commentoOpt = this.commentoService.findById(id);
        if (commentoOpt.isEmpty() || principal == null) {
            return "redirect:/partite";
        }

        Commento commento = commentoOpt.get();
        // Controllo di Sicurezza: l'utente loggato è l'effettivo autore del commento?
        if (!commento.getAutore().getUsername().equals(principal.getName())) {
            return "redirect:/partite/" + commento.getPartita().getId(); 
        }

        model.addAttribute("commento", commento);
        
        // FIX CRUCIALE: Passiamo la partita legata a questo commento al modello, 
        // altrimenti l'espressione 'partita.squadraCasa.nome' nel form va in crash!
        model.addAttribute("partita", commento.getPartita()); 
        
        return "commenti/form";
    }

 // 4. SALVATAGGIO MODIFICA (POST): Riceve l'oggetto modificato e aggiorna il database
    @PostMapping("/commenti/{id}/edit")
    public String updateCommento(@PathVariable Long id, 
                                 @Valid @ModelAttribute("commento") Commento commentoModificato, 
                                 BindingResult bindingResult,
                                 Principal principal,
                                 Model model) {
        
        Optional<Commento> commentoOpt = this.commentoService.findById(id);
        if (commentoOpt.isEmpty() || principal == null) {
            return "redirect:/partite";
        }

        Commento commentoDb = commentoOpt.get();
        if (!commentoDb.getAutore().getUsername().equals(principal.getName())) {
            return "redirect:/partite/" + commentoDb.getPartita().getId();
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("partita", commentoDb.getPartita());
            return "commenti/form";
        }

        // 1. Aggiorna i dati testuali modificati dall'utente mantenendo le FK intatte
        commentoDb.setTitolo(commentoModificato.getTitolo());
        commentoDb.setTesto(commentoModificato.getTesto());
        
        // 2. Eseguiamo il salvataggio sul record esistente (fa un UPDATE grazie all'id)
        this.commentoService.save(commentoDb);
        
        // 3. Reindirizza alla pagina di dettaglio pubblica della partita
        return "redirect:/partite/" + commentoDb.getPartita().getId();
    } // <-- Chiusura del metodo corretta
}