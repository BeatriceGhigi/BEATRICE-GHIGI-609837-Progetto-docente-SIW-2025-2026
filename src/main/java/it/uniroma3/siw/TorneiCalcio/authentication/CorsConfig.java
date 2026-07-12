package it.uniroma3.siw.TorneiCalcio.authentication;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * Permette al frontend React (Vite, http://localhost:5173) di
 * chiamare gli endpoint REST sotto /api/** esposti da questo backend
 * (http://localhost:8080), che il browser vedrebbe altrimenti come
 * origini diverse e bloccherebbe.
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
@Override
public void addCorsMappings(CorsRegistry registry) {
registry.addMapping("/rest/**").allowedOrigins("http://localhost:5173")
.allowedMethods("GET", "POST", "PUT", "DELETE");
}
}