package it.uniroma3.siw.TorneiCalcio.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Squadra {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@Column(nullable=false)
	private String nome;
	
	@NotBlank
	@Column(nullable=false)
	private String città;
	
	@NotNull
	@Column(nullable=false)
	private Integer annoFondazione;
	
	@OneToMany(mappedBy = "squadra", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Giocatore> giocatori;
	
	@ManyToMany(mappedBy = "squadre") 
	private List<Torneo> tornei;
	
//COSTRUTTORE
	public Squadra() {
		super();
	}


//GET & SET
	
	
	public String getNome() {
		return nome;
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


	public String getCittà() {
		return città;
	}


	public void setCittà(String città) {
		this.città = città;
	}


	public Integer getAnnoFondazione() {
		return annoFondazione;
	}


	public void setAnnoFondazione(Integer annoFondazione) {
		this.annoFondazione = annoFondazione;
	}

	

public List<Giocatore> getGiocatori() {
		return giocatori;
	}


	public void setGiocatori(List<Giocatore> giocatori) {
		this.giocatori = giocatori;
	}


	public List<Torneo> getTornei() {
		return tornei;
	}


	public void setTornei(List<Torneo> tornei) {
		this.tornei = tornei;
	}


	//EQAULS & HASHCODE
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
		Squadra other = (Squadra) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
	
}
