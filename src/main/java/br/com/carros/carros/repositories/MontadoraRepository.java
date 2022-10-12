package br.com.carros.carros.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.carros.carros.model.Montadora;

@Repository
public interface MontadoraRepository extends CrudRepository<Montadora, Long> {
	Optional<Montadora> findByNome(String nome);
}
