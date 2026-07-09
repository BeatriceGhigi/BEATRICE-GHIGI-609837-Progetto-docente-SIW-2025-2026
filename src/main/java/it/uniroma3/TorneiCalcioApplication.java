package it.uniroma3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TorneiCalcioApplication {

	public static void main(String[] args) {
		SpringApplication.run(TorneiCalcioApplication.class, args);
		
		// Crea un'istanza del cifratore usato dal prof
		PasswordEncoder encoder = new BCryptPasswordEncoder();

		// Cifra la parola 'admin'
		String passwordCifrata = encoder.encode("admin");

		// Stampa il risultato in console
		System.out.println("ECCO L'HASH GENERATO DA JAVAA: " + passwordCifrata);
	}

}
