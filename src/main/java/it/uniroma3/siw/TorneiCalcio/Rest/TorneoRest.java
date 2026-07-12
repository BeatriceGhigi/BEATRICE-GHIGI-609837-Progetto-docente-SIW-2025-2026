package it.uniroma3.siw.TorneiCalcio.Rest;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.uniroma3.siw.TorneiCalcio.model.ClassificaDto;
import it.uniroma3.siw.TorneiCalcio.model.Torneo;
import it.uniroma3.siw.TorneiCalcio.service.TorneoService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TorneoRest {

    private final TorneoService torneoService;

    // Dependency Injection tramite costruttore
    public TorneoRest(TorneoService torneoService) {
        this.torneoService = torneoService;
    }

    // Serve a React all'avvio dell'app per popolare la select <select>
    @GetMapping("/tornei")
    public List<Torneo> getAllTornei() {
        return this.torneoService.findAll();
    }

    // Serve a React ogni volta che selezioni un torneo per caricare la tabella
    @GetMapping("/tornei/{id}/classifica")
    public List<ClassificaDto> getClassificaTorneo(@PathVariable Long id) {
        return this.torneoService.generaClassifica(id);
    }
}