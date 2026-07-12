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

	public TorneoRest(TorneoService torneoService) {
		this.torneoService = torneoService;
	}


	@GetMapping
	public List<TorneoSummaryDto> list() {
		return torneoService.findAll().stream()
				.map(t -> new TorneoSummaryDto(t.getId(), t.getNome(), t.getAnno()))
				.collect(Collectors.toList());
	}


	@GetMapping("/{id}/classifica")
	public List<ClassificaDto> classifica(@PathVariable Long id) {
		return torneoService.generaClassifica(id);
	}
}