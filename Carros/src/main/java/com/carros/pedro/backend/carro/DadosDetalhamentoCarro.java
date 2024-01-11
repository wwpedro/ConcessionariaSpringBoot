package com.carros.pedro.backend.carro;

public record DadosDetalhamentoCarro(
		Long id,
		String marca,
	    String modelo,
	    Concessionaria concessionaria,
	    int ano,
	    String cor,
	    float preco,
	    boolean ativo) {
	public DadosDetalhamentoCarro(Carro carro) {
		this(carro.getId(),
			carro.getMarca(),
			carro.getModelo(),
			carro.getConcessionaria(),
			carro.getAno(),carro.getCor(),
			carro.getPreco(),
			carro.getAtivo());
	}
}
