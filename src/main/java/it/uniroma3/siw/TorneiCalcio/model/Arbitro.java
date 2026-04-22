package it.uniroma3.siw.TorneiCalcio.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Arbitro {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String nome;
	
	@Column(nullable=false)
	private String cognome;
	
	@Column(nullable=false, unique=true)
	private Integer codiceArbitrale;

	
//COSTRUTTORE
	public Arbitro() {
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

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Integer getCodiceArbitrale() {
		return codiceArbitrale;
	}

	public void setCodiceArbitrale(Integer codiceArbitrale) {
		this.codiceArbitrale = codiceArbitrale;
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
		Arbitro other = (Arbitro) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
