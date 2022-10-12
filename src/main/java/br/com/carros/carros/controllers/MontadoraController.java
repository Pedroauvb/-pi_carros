package br.com.carros.carros.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.carros.carros.model.Montadora;
import br.com.carros.carros.repositories.MontadoraRepository;

@RestController
@RequestMapping("/api/montadora")
public class MontadoraController {
	
	@Autowired
	private MontadoraRepository montadoraRepository;
	
	@GetMapping
	public List<Montadora> getMontadoras() {
		return (List<Montadora>) montadoraRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<?> inserirMontadora(@RequestBody Montadora montadora) {
		
		final Optional<Montadora> montadoraResult = montadoraRepository.findByNome(montadora.getNome());
		if(!montadoraResult.isPresent()) {
			final Montadora montadoraSalva = montadoraRepository.save(montadora);
			return ResponseEntity.ok(montadoraSalva);
		}
		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Montadora ja existente!!!");
	}
	
	

}
