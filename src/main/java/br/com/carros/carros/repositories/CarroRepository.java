package br.com.carros.carros.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.carros.carros.model.Carro;

public interface CarroRepository extends CrudRepository<Carro, Long>{

}
