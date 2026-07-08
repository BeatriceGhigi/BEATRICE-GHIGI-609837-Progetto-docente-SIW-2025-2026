package it.uniroma3.TorneiCalcio.controller.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.uniroma3.siw.TorneiCalcio.model.Torneo;
import it.uniroma3.siw.TorneiCalcio.service.TorneoService;


@RestController
@RequestMapping("/rest/tornei")
public class RestTorneoController {
	private final TorneoService torneoService;

  

    public RestTorneoController(TorneoService torneoService) {
		this.torneoService = torneoService;
	}

	@GetMapping
    public List<Torneo> list() {
        return torneoService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Torneo> show(@PathVariable Long id) {
        return torneoService.findById(id);
    }
}
