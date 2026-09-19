package com.kreuch.api_alunos.controller;

import java.util.List;

import com.kreuch.api_alunos.dto.AlunoRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kreuch.api_alunos.dto.AlunoResponse;
import com.kreuch.api_alunos.service.AlunoService;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

	private final AlunoService service;

	public AlunoController(AlunoService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<AlunoResponse>> listarAlunos() {
		return ResponseEntity.status(HttpStatus.OK).body(service.listarAlunos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<AlunoResponse> obterAlunoPorId(@PathVariable int id) {
		return ResponseEntity.status(HttpStatus.OK).body(service.obterAlunoPorId(id));
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<AlunoResponse> cadastarAluno(@Valid @RequestBody AlunoRequest request){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarAluno(request));
	}

	@PutMapping("/{id}")
	public ResponseEntity<AlunoResponse> atualizarAluno(@PathVariable int id, @Valid @RequestBody AlunoRequest request){
		return ResponseEntity.status(HttpStatus.OK).body(service.atualizarAluno(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deletarAluno(@PathVariable int id){
		service.deletarAluno(id);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
