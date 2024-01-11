package com.carros.pedro.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.carros.pedro.backend.carro.DadosDetalhamentoCarro;
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
	public ResponseEntity<List<DadosListagemCarros>> listar(){
		var lista = repository.findAllByAtivoTrue().stream().map(DadosListagemCarros::new).toList();
		return ResponseEntity.ok(lista);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoCarro> atualizar(@RequestBody @Valid DadosAtualizarCarro dados) {
		var carro = repository.getReferenceById(dados.id());
		carro.atualizarInformacoes(dados);
		
		return ResponseEntity.ok(new DadosDetalhamentoCarro(carro));
	}
	//(tem exclusão padrão e exclusão logica)
	
	@DeleteMapping("/{id}") // id dinamicco
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		repository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("inativar/{id}")
	@Transactional
	public ResponseEntity<Void> inativar(@PathVariable Long id) {
		var carro = repository.getReferenceById(id);
		carro.inativar();
		return ResponseEntity.noContent().build();
	}
	@PutMapping("ativar/{id}")
	@Transactional
	public ResponseEntity<Void> ativar(@PathVariable Long id) {
		var carro = repository.getReferenceById(id);
		carro.ativar();
		return ResponseEntity.noContent().build();
	}
	
}
