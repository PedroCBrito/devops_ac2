package com.tdd.ac2.dto;

import java.util.List;

public class AlunoDTO {
    
    private Long id;
    private String nome;
    private String email;
    private List<String> cursos;  // Lista de nomes dos cursos

    // Construtor
    public AlunoDTO(Long id, String nome, String email, List<String> cursos) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cursos = cursos;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<String> getCursos() {
        return cursos;
    }

    public void setCursos(List<String> cursos) {
        this.cursos = cursos;
    }
}
