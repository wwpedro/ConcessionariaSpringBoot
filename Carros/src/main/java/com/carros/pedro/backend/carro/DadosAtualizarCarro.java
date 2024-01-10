package com.carros.pedro.backend.carro;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarCarro(
		
		@NotNull
		long id,
		
		String marca,
	    String modelo,
	    Concessionaria concessionaria,
	    int ano,
	    String cor,
	    float preco) {

}
