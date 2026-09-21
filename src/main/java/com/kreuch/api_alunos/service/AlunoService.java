package com.kreuch.api_alunos.service;

import java.util.ArrayList;
import java.util.List;

import com.kreuch.api_alunos.dto.AlunoRequest;
import com.kreuch.api_alunos.exception.AlunoNaoEncontradoException;
import com.kreuch.api_alunos.exception.EmailJaCadastradoException;
import org.springframework.stereotype.Service;

import com.kreuch.api_alunos.dto.AlunoResponse;
import com.kreuch.api_alunos.model.Aluno;

@Service
public class AlunoService {

	private final List<Aluno> alunos;

	private int id = 1;

	public AlunoService() {
		alunos = new ArrayList<Aluno>();
	}

	public List<AlunoResponse> listarAlunos() {

        List<AlunoResponse> alunosResponse = new ArrayList<>();

		for (Aluno a : alunos) {
			alunosResponse
					.add(new AlunoResponse(a.getId(), a.getNome(), a.getEmail(), a.getDataNascimento(), a.getMedia()));
		}
		return alunosResponse;

	}

	public AlunoResponse obterAlunoPorId(int id) {
		for (Aluno a : alunos) {
			if (a.getId() == id) {
				return new AlunoResponse(id, a.getNome(), a.getEmail(), a.getDataNascimento(), a.getMedia());
			}
		}
		throw new AlunoNaoEncontradoException("Aluno não encontrado");
	}

	public AlunoResponse cadastrarAluno(AlunoRequest request) {

		//if (alunos.stream().anyMatch(aluno -> aluno.getEmail().equals(request.getEmail()))) throw new RuntimeException("Email ja cadastrado");

		for (Aluno a : alunos){
			if (a.getEmail().equalsIgnoreCase(request.getEmail())) throw new EmailJaCadastradoException("Email ja cadastrado");
		}

		Aluno alunoCadastrado = (new Aluno(id++, request.getNome(), request.getEmail(), request.getSenha(), request.getDataNascimento(), request.getMedia()));

		alunos.add(alunoCadastrado);

		return new AlunoResponse(alunoCadastrado.getId(), alunoCadastrado.getNome(), alunoCadastrado.getEmail(), alunoCadastrado.getDataNascimento(), alunoCadastrado.getMedia());
	}

	public AlunoResponse atualizarAluno(int id, AlunoRequest request) {


		for (Aluno a : alunos){
			if (a.getEmail().equalsIgnoreCase(request.getEmail()) && (a.getId() != id)) throw new EmailJaCadastradoException("Email ja cadastrado");
		}

		for (Aluno a : alunos) {
			if (a.getId() == id) {

				a.setNome(request.getNome());
				a.setEmail(request.getEmail());
				a.setSenha(request.getSenha());
				a.setDataNascimento(request.getDataNascimento());
				a.setMedia(request.getMedia());

				return new AlunoResponse(a.getId(), a.getNome(), a.getEmail(), a.getDataNascimento(), a.getMedia());
			}
		}
		throw new AlunoNaoEncontradoException("Aluno não encontrado");
	}

	public void deletarAluno(int id){
		for (Aluno a : alunos){
			if (a.getId() == id) {
				alunos.remove(a);
				return;
			}
		}
	}
}