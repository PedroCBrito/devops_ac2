package com.tdd.ac2.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_aluno")
public class Aluno {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;
    
    private String nome;
    
    @OneToMany  // Define o relacionamento com a entidade Curso (se for mapeada)
    private List<Curso> cursos = new ArrayList<>();
    
    @Embedded
    private Aluno_Email email;
    
    public Aluno(String nome, String alunoEmail) {
        this.nome = nome;
        this.email = new Aluno_Email(alunoEmail);
    }
    
    public Aluno() {
 
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

    public Aluno_Email getEmail() {
        return email;
    }

    public void setEmail(Aluno_Email email) {
        this.email = email;
    }
    
    // Função para adicionar um novo curso à lista de cursos
    public void addCurso(Curso curso) {
        cursos.add(curso);
    }
    
    // Função para verificar se o aluno já possui um curso específico
    public boolean temCurso(Curso novoCurso) {
        return cursos.contains(novoCurso);
    }
    
 // Função para obter a lista de cursos do aluno
    public List<Curso> getCursos() {
        return cursos;
    }

}
