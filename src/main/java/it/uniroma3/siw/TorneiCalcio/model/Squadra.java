package it.uniroma3.siw.TorneiCalcio.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Squadra {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String nome;
	
	@Column(nullable=false)
	private String città;
	
	@Column(nullable=false)
	private Integer annoFondazione;
	
//COSTRUTTORE
	public Squadra() {
		super();
	}


//GET & SET
	public String getNome() {
		return nome;
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
