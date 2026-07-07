package it.uniroma3.siw.TorneiCalcio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.uniroma3.siw.TorneiCalcio.model.Credentials;

public interface CredentialsRepository extends JpaRepository<Credentials,Long>{

	public Optional<Credentials> findByUsername(String username);

}
