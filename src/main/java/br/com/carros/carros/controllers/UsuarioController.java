package br.com.carros.carros.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.carros.carros.model.Usuario;
import br.com.carros.carros.repositories.UsuarioRepository;
import br.com.carros.carros.service.UserDetailsImpl;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping
	public List<Usuario> getUsuarios() {
		return (List<Usuario>) usuarioRepository.findAll();
	}

	@PostMapping
	public Usuario novoUsuario(@RequestBody Usuario novoUsuario) throws Exception {
		final Optional<Usuario> usuarioResponse = usuarioRepository.findByEmail(novoUsuario.getEmail());

		if (!usuarioResponse.isPresent()) {

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			final String password = passwordEncoder.encode(novoUsuario.getSenha());
			novoUsuario.setSenha(password);
			return usuarioRepository.save(novoUsuario);

		} else {
			throw new Exception("Usuario ja existente");
		}

	}

	public Usuario getUsuarioLogado() {
		UserDetailsImpl usuarioLogado = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		final Optional<Usuario> usuario = usuarioRepository.findByEmail(usuarioLogado.getEmail());
		return usuario.get();

	}
}
