package it.uniroma3.siw.TorneiCalcio.model;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.OneToOne;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Giocatore {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@Column(nullable=false)
	private String nome;
	
	@NotBlank
	@Column(nullable=false)
	private String cognome;
	
	@NotBlank
	@Column(nullable=false)
	private LocalDate dataDiNascita;
	
	@Column
	private Integer altezza;
	
	@ManyToOne
	private Squadra squadra;
	
//COSTRUTTORE
	public Giocatore() {
		
	}
	
//GET & SET

	
	
	public String getNome() {
		return nome;
	}

	public Squadra getSquadra() {
		return squadra;
	}

	public void setSquadra(Squadra squadra) {
		this.squadra = squadra;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public LocalDate getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(LocalDate dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public Integer getAltezza() {
		return altezza;
	}

	public void setAltezza(Integer altezza) {
		this.altezza = altezza;
	}

	
	
//HASHCODE & EQUALS
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Giocatore other = (Giocatore) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
	
}
