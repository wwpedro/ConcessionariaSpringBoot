package com.carros.pedro.backend.carro;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Long> {

	List<Carro> findAllByAtivoTrue();
	
}
