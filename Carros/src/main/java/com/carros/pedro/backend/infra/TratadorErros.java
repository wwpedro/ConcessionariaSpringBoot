package com.carros.pedro.backend.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice // toda vez que tiver exeption e vais bustar e ver se trata nessa classe o erro e retorna
public class TratadorErros {
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> tratador404() {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> tratador400(MethodArgumentNotValidException ex){
		var erros = ex.getFieldErrors();
		
		return ResponseEntity.badRequest().body(erros.stream().map(DadosErros::new).toList());
	} 
	
	//pra n ficar cheio de array doido , tem que tratar no record
	public record DadosErros(String field, String message) {
		public DadosErros(FieldError erro) {
			this(erro.getField(), erro.getDefaultMessage());
		}
	}
}
