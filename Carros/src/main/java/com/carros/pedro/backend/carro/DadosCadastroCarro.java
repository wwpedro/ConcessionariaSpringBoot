package com.carros.pedro.backend.carro;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroCarro(
		
		@NotBlank
		String marca,
		@NotBlank
	    String modelo,
	    @Enumerated
	    Concessionaria concessionaria,
	    @NotNull
	    int ano,
	    @NotBlank
	    String cor,
	    @NotNull
	    float preco) {}
