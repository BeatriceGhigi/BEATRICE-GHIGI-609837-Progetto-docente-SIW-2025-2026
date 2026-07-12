package it.uniroma3.siw.TorneiCalcio.model;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import  jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class Partita {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Column(nullable=false)
	private LocalDateTime programmazione;
	
	@NotNull
	private Integer goalsHome;
	
	@NotNull
	private Integer goalsAway;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusPartita stato; 

	public enum StatusPartita{
		SCHEDULED,
		PLAYED,
		CANCELED,	
	}
	
	@ManyToOne
	private Arbitro arbitro;
	
	@ManyToOne

	private Squadra squadraCasa;
	
	@ManyToOne
	private Squadra squadraOspite;
	
	@ManyToOne
	
	private Torneo torneo;
	
	@OneToMany(mappedBy="partita",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Commento> commenti;
	
//COSTRUTTORE
	public Partita() {
		super();
	}

	
//GE & SET
	
   public LocalDateTime getProgrammazione() {
		return programmazione;
	}


	public Long getId() {
	return id;
}


   public void setId(Long id) {
	this.id = id;
   }


	public void setProgrammazione(LocalDateTime programmazione) {
		this.programmazione = programmazione;
	}


	public StatusPartita getStato() {
		return stato;
	}


	public void setStato(StatusPartita stato) {
		this.stato = stato;
	}

	public Integer getGoalsHome() {
		return goalsHome;
	}

	
	public void setGoalsHome(Integer goalsHome) {
		this.goalsHome = goalsHome;
	}

	public Integer getGoalsAway() {
		return goalsAway;
	}

	public void setGoalsAway(Integer goalsAway) {
		this.goalsAway = goalsAway;
	}

	
public Arbitro getArbitro() {
		return arbitro;
	}


	public void setArbitro(Arbitro arbitro) {
		this.arbitro = arbitro;
	}


	public Squadra getSquadraCasa() {
		return squadraCasa;
	}


	public void setSquadraCasa(Squadra squadraCasa) {
		this.squadraCasa = squadraCasa;
	}


	public Squadra getSquadraOspite() {
		return squadraOspite;
	}


	public void setSquadraOspite(Squadra squadraOspite) {
		this.squadraOspite = squadraOspite;
	}


	public Torneo getTorneo() {
		return torneo;
	}


	public void setTorneo(Torneo torneo) {
		this.torneo = torneo;
	}

	

	public List<Commento> getCommenti() {
		return commenti;
	}


	public void setCommenti(List<Commento> commenti) {
		this.commenti = commenti;
	}


	//EQUALS & HASHCODE
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
		Partita other = (Partita) obj;
		return Objects.equals(id, other.id);
	}
	
	


	
}
