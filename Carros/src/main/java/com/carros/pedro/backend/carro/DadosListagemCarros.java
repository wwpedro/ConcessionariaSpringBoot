package com.carros.pedro.backend.carro;

public record DadosListagemCarros(
		Long id,
		String marca,
		String modelo,
		int ano,
		String cor,
		float preco,
		Concessionaria concessionaria) {
	
	public DadosListagemCarros(Carro carro) {
		this(carro.getId(),
			carro.getMarca(),
			carro.getModelo(),
			carro.getAno(),
			carro.getCor(),
			carro.getPreco(),
			carro.getConcessionaria());
	}
}
