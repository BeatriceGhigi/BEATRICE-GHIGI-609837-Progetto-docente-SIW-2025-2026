package it.uniroma3.siw.TorneiCalcio.controller.Rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.uniroma3.siw.TorneiCalcio.model.ClassificaDto;
import it.uniroma3.siw.TorneiCalcio.model.TorneoSummaryDto;
import it.uniroma3.siw.TorneiCalcio.service.TorneoService;

@RestController
@RequestMapping("/rest/tornei")
public class TorneoRest {

    private final TorneoService torneoService;

    // Dependency Injection tramite costruttore, esattamente come il prof
    public TorneoRest(TorneoService torneoService) {
        this.torneoService = torneoService;
    }

    // 1. Endpoint per la select su React (Lista di tutti i tornei)
    // Ritorna un DTO leggero (id, nome, anno) invece dell'entità Torneo completa:
    // l'entità ha relazioni (partite, commenti, autore) che senza DTO
    // verrebbero serializzate in JSON, generando riferimenti circolari
    // (Torneo -> Partita -> Torneo...) e potenzialmente esponendo dati sensibili
    // (es. la password degli utenti dentro Commento.autore).
    @GetMapping
    public List<TorneoSummaryDto> list() {
        return torneoService.findAll().stream()
                .map(t -> new TorneoSummaryDto(t.getId(), t.getNome(), t.getAnno()))
                .collect(Collectors.toList());
    }

    // 2. Endpoint per la classifica (Usa il tuo ClassificaDto originale a 5 campi)
    @GetMapping("/{id}/classifica")
    public List<ClassificaDto> classifica(@PathVariable Long id) {
        return torneoService.generaClassifica(id);
    }
}