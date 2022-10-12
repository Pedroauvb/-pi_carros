package br.com.carros.carros.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.carros.carros.model.Carro;
import br.com.carros.carros.repositories.CarroRepository;

@RestController
@RequestMapping("/api/carro")
public class CarroController {
	@Autowired
	CarroRepository carroRepository;

	@Autowired
	private UsuarioController usuarioController;

	@GetMapping
	List<Carro> getCarros() {
		return (List<Carro>) carroRepository.findAll();
	}

	@PostMapping
	ResponseEntity<?> inserirCarro(@RequestBody Carro carro) {
		try {

			carro.setUsuario(usuarioController.getUsuarioLogado());
			final Carro salvo = carroRepository.save(carro);
			return ResponseEntity.ok(salvo);

		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao cadastrar carro!!!");
		}

	}

	@DeleteMapping("/{id}")
	ResponseEntity<?> deletarCarro(@PathVariable Long id) {
		try {
			carroRepository.deleteById(id);
			return ResponseEntity.ok("Carro excluido com sucesso!!!");
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao excluir o carro!!!");
		}
	}

	@PutMapping("/{id}")
	ResponseEntity<?> alterarCarro(@RequestBody Carro carro, @PathVariable Long id) {
		try {
			final Optional<Carro> carroEncontrado = carroRepository.findById(id);
			if (carroEncontrado.isPresent()) {
				carroEncontrado.get().setNome(carro.getNome());
				carroEncontrado.get().setMontadora(carro.getMontadora());
				return ResponseEntity.ok(carroRepository.save(carroEncontrado.get()));

			}
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Carro não localizado!!!");
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao excluir o carro!!!");
		}
	}

}
