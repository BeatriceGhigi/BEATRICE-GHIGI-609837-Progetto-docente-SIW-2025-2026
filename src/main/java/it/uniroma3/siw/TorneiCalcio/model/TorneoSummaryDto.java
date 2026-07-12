package it.uniroma3.siw.TorneiCalcio.model;

// DTO leggero per l'endpoint REST /rest/tornei:
// espone solo i campi che servono al frontend React,
// evitando di serializzare l'intera entità (e i suoi riferimenti circolari).
public class TorneoSummaryDto {

    private Long id;
    private String nome;
    private Integer anno;

    public TorneoSummaryDto(Long id, String nome, Integer anno) {
        this.id = id;
        this.nome = nome;
        this.anno = anno;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getAnno() {
        return anno;
    }
}