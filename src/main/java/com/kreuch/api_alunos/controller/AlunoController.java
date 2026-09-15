package com.kreuch.api_alunos.controller;

import java.util.List;

import com.kreuch.api_alunos.dto.AlunoRequest;
import jakarta.validation.Valid;
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
	public List<AlunoResponse> listarAlunos() {
		return service.listarAlunos();
	}

	@GetMapping("/{id}")
	public AlunoResponse obterAlunoPorId(@PathVariable int id) {
		return service.obterAlunoPorId(id);
	}

	@PostMapping("/cadastrar")
	public AlunoResponse cadastarAluno(@Valid @RequestBody AlunoRequest request){
		return service.cadastrarAluno(request);
	}
}
