package br.com.carros.carros.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.carros.carros.model.VendaCarro;

public interface VendaCarroRepository extends CrudRepository<VendaCarro, Long> {

}
