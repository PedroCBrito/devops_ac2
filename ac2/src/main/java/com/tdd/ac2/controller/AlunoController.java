package com.tdd.ac2.controller;

import com.tdd.ac2.dto.AlunoDTO;
import com.tdd.ac2.entity.Aluno;
import com.tdd.ac2.service.AlunoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    // Construtor
    @Autowired
    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    // Método para converter Aluno em AlunoDTO
    private AlunoDTO converterParaDTO(Aluno aluno) {
        List<String> cursos = aluno.getCursos().stream()
                                    .map(curso -> curso.getNome()) // Supondo que Curso tem o método getNome()
                                    .collect(Collectors.toList());

        return new AlunoDTO(aluno.getId(), aluno.getNome(), aluno.getEmail().getEmailAddress(), cursos);
    }

    // Endpoint para buscar um aluno pelo ID
    @GetMapping("/{id}")
    public AlunoDTO getAluno(@PathVariable Long id) {
        Aluno aluno = alunoService.buscarAluno(id);  // Método do serviço que busca o aluno
        if (aluno == null) {
            throw new RuntimeException("Aluno não encontrado com id: " + id);
        }
        return converterParaDTO(aluno);  // Converte a entidade para DTO
    }

    // Endpoint para listar todos os alunos
    @GetMapping
    public List<AlunoDTO> getAllAlunos() {
        List<Aluno> alunos = alunoService.listarTodosAlunos();  // Método que retorna todos os alunos
        return alunos.stream()
                     .map(this::converterParaDTO)  // Converte cada aluno para DTO
                     .collect(Collectors.toList());
    }
}
