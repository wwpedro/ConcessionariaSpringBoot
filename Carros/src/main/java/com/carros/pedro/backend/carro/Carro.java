package com.carros.pedro.backend.carro;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name="carros")
@Entity(name = "carro")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
@Getter
@Setter
public class Carro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String marca;
	private String modelo;
	private int ano;
	private String cor;
	private float preco;
	@Enumerated(EnumType.STRING)
	private Concessionaria concessionaria;
	private boolean ativo;

	
	public Carro() {}
	
	public Carro(DadosCadastroCarro dados) {
		this.ativo = true;
		this.marca = dados.marca();
		this.modelo = dados.modelo();
		this.ano = dados.ano();
		this.cor = dados.cor();
		this.preco = dados.preco();
		this.concessionaria = dados.concessionaria();
	}
	
	
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public Concessionaria getConcessionaria() {
		return concessionaria;
	}

	public void setConcessionaria(Concessionaria concessionaria) {
		this.concessionaria = concessionaria;
	}
	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	

	public void atualizarInformacoes(@Valid DadosAtualizarCarro dados) {
		if(dados.marca() != null) {
			this.marca = dados.marca();
		}
		if(dados.modelo() != null) {
			this.modelo = dados.modelo();
		}
		if(dados.concessionaria() != null) {
			this.concessionaria = dados.concessionaria();
		}
			
		if(dados.ano() != 0) {
				this.ano = dados.ano();
		}
		
		if(dados.cor() != null) {
			this.cor = dados.cor();
		}
		
		if(dados.preco() != 0) {
			this.preco = dados.preco();
		}
		
		
		
	}

	public void inativar() {
		this.ativo = false;
		
	}

	public void ativar() {
		this.ativo = true;
		
	}
	
}
