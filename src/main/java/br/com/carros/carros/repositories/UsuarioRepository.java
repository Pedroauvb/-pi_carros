package br.com.carros.carros.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.carros.carros.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	Optional<Usuario> findByNome(String nome);
	Optional<Usuario> findByEmail(String email);
}
