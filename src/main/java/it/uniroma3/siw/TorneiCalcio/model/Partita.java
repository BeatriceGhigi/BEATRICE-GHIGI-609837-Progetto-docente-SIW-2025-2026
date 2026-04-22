package it.uniroma3.siw.TorneiCalcio.model;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Partita {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private LocalDateTime programmazione;
	
	private Integer goalsHome;
	
	private Integer goalsAway;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus stato; 

	public enum OrderStatus{
		SCHEDULED,
		PLAYED,
		CANCELED,	
	}
	
	@ManyToOne
	private Arbitro arbitro;
	
	@ManyToMany(mappedBy="partite")
	private List<Squadra> squadre;
	
	
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


	public OrderStatus getStato() {
		return stato;
	}


	public void setStato(OrderStatus stato) {
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
