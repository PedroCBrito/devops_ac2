package com.tdd.ac2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdd.ac2.entity.Aluno;
import com.tdd.ac2.repository.AlunoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    @Autowired
    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    // Buscar aluno por ID
    public Aluno buscarAluno(Long id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        return aluno.orElse(null);  // Retorna o aluno ou null se não encontrado
    }

    // Listar todos os alunos
    public List<Aluno> listarTodosAlunos() {
        return alunoRepository.findAll();
    }
}
