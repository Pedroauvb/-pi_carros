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

import br.com.carros.carros.model.VendaCarro;
import br.com.carros.carros.repositories.VendaCarroRepository;

@RestController
@RequestMapping("/api/venda_carro")
public class VendaCarroController {
	@Autowired
	VendaCarroRepository vendaCarroRepository;

	@Autowired
	UsuarioController usuarioController;

	@GetMapping
	List<VendaCarro> getCarrosParaVenda() {
		return (List<VendaCarro>) vendaCarroRepository.findAll();
	}

	@PostMapping
	ResponseEntity<?> inserirCarroVenda(@RequestBody VendaCarro vendaCarro) {
		try {

			vendaCarro.setUsuarioId(usuarioController.getUsuarioLogado().getId());
			final VendaCarro salvo = vendaCarroRepository.save(vendaCarro);
			return ResponseEntity.ok(salvo);

		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao inserir carro para venda!!!");
		}

	}

	@DeleteMapping("/{id}")
	ResponseEntity<?> deletarVendaCarro(@PathVariable Long id) {
		try {
			vendaCarroRepository.deleteById(id);
			return ResponseEntity.ok("Carro excluido com sucesso!!!");
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao excluir o carro da venda!!!");
		}
	}

	@PutMapping("/{id}")
	ResponseEntity<?> alterarVendaCarro(@RequestBody VendaCarro vendaCarro, @PathVariable Long id) {
		try {
			final Optional<VendaCarro> carroEncontrado = vendaCarroRepository.findById(id);
			if (carroEncontrado.isPresent()) {
				carroEncontrado.get().setNomeCarro(vendaCarro.getNomeCarro());
				carroEncontrado.get().setValor(vendaCarro.getValor());
				return ResponseEntity.ok(vendaCarroRepository.save(carroEncontrado.get()));

			}
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Venda não localizada!!!");
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao excluir o carro da venda!!!");
		}
	}

}
