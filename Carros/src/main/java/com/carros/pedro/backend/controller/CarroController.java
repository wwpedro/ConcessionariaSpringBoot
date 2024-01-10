package com.carros.pedro.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carros.pedro.backend.carro.Carro;
import com.carros.pedro.backend.carro.CarroRepository;
import com.carros.pedro.backend.carro.DadosAtualizarCarro;
import com.carros.pedro.backend.carro.DadosCadastroCarro;
import com.carros.pedro.backend.carro.DadosListagemCarros;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/carros")
public class CarroController {
	
	@Autowired
	private CarroRepository repository;
	
	@PostMapping
	@Transactional //responsavel para reverter a transação problematica
	public void cadastrar(@RequestBody @Valid DadosCadastroCarro dados) {
		repository.save(new Carro(dados));
	}
	
	@GetMapping
	public List<DadosListagemCarros> listar(){
		return repository.findAllByAtivoTrue().stream().map(DadosListagemCarros::new).toList();
	}
	
	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizarCarro dados) {
		var carro = repository.getReferenceById(dados.id());
		carro.atualizarInformacoes(dados);
	}
	//(tem exclusão padrão e exclusão logica)
	
	@DeleteMapping("/{id}") // id dinamicco
	public void excluir(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	@DeleteMapping("inativar/{id}")
	@Transactional
	public void inativar(@PathVariable Long id) {
		var carro = repository.getReferenceById(id);
		carro.inativar();
	}
	@PutMapping("ativar/{id}")
	@Transactional
	public void ativar(@PathVariable Long id) {
		var carro = repository.getReferenceById(id);
		carro.ativar();
	}
	
}
