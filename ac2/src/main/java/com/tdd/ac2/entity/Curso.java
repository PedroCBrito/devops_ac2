package com.tdd.ac2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_curso") // Define a tabela no banco de dados
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define o ID como chave primária auto incrementada
    private Long id;
    
    private String nome;

    // Construtor padrão necessário para o JPA
    public Curso() {
    }

    // Construtor com nome
    public Curso(String nome) {
        this.nome = nome;
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
}
