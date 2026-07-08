package it.uniroma3.siw.TorneiCalcio.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Commento {

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;

@NotBlank
@Column(length=2000, nullable=false)
private String testo;

@ManyToOne
private Utente autore;

@ManyToOne
private Partita partita;

public Commento() {
	
}

public String getTesto() {
	return testo;
}

public void setTesto(String testo) {
	this.testo = testo;
}

public Utente getAutore() {
	return autore;
}

public void setAutore(Utente autore) {
	this.autore = autore;
}

public Partita getPartita() {
	return partita;
}

public void setPartita(Partita partita) {
	this.partita = partita;
}

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
	Commento other = (Commento) obj;
	return Objects.equals(id, other.id);
}



}
