package br.com.carros.carros.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.carros.carros.auth.AuthRequest;
import br.com.carros.carros.auth.AuthResponse;
import br.com.carros.carros.config.JwtTokenUtil;
import br.com.carros.carros.model.Usuario;

@RestController
@RequestMapping("/api/auth")
public class LoginController {
	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	JwtTokenUtil jwtUtil;
	
	@Autowired
	UsuarioController usuarioController;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest request) {
		try {
			Authentication authentication = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtil.generateJwtToken(authentication);
			
			final AuthResponse response = new AuthResponse(jwt);
			return ResponseEntity.ok(response);

		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario ) {
		try {
			final Usuario usuarioRegistrado = usuarioController.novoUsuario(usuario);
			return ResponseEntity.ok(usuarioRegistrado);
		}catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
				
	}
}
