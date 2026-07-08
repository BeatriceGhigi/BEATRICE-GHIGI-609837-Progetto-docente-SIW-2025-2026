package it.uniroma3.siw.TorneiCalcio.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Utente {
	
	public static final String USER_ROLE = "USER";
	public static final String ADMIN_ROLE = "ADMIN";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@Column(nullable=false, unique=true)
	private String username;
	
	@NotBlank
	@Column(nullable=false)
	private String password;
	
	@NotBlank
	@Column(nullable=false)
	private String ruolo;
	
	@OneToMany(mappedBy="autore")
	private List<Commento> commenti;

//COSTRUTTORE
	public Utente() {
		super();
	}

	
//GET & SET
	
	public String getUsername() {
		return username;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	
public List<Commento> getCommenti() {
		return commenti;
	}


	public void setCommenti(List<Commento> commenti) {
		this.commenti = commenti;
	}
	
	
	public static String getUserRole() {
		return USER_ROLE;
	}


	public static String getAdminRole() {
		return ADMIN_ROLE;
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
		Utente other = (Utente) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	

}
