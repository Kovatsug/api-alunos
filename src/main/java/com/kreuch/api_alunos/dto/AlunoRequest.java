package com.kreuch.api_alunos.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class AlunoRequest {

    @NotBlank(message = "Nome não pode ser vazio ai")
    @Size(min = 3,max = 51, message = "tamnho maximo de 51 minimo 3")
    private String nome;

    @NotBlank(message = "email não pode ser vazio")
    @Email(message = "Email invalido")
    private String email;

    @NotBlank(message = "Senha não pode ser vazia")
    @Size(min = 7,max = 50, message = "Tamanho min:7 max:50")
    private String senha;

    @NotNull(message = "Data não pode ser nula")
    @PastOrPresent(message = "Data invalida")
    private LocalDate dataNascimento;

    @Min(value = 0, message = "numero deve ser positivo")
    @Max(value = 10, message = "Numero maximo é 10")
    private double media;

    public AlunoRequest(String nome, String email, String senha, LocalDate dataNascimento, double media) {
        super();
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.media = media;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }
}
