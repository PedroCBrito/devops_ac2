package com.tdd.ac2.controller;

import com.tdd.ac2.dto.AlunoDTO;
import com.tdd.ac2.entity.Aluno;
import com.tdd.ac2.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    @Autowired
    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    private AlunoDTO converterParaDTO(Aluno aluno) {
        List<String> cursos = aluno.getCursos().stream()
                .map(curso -> curso.getNome())
                .collect(Collectors.toList());
        return new AlunoDTO(aluno.getId(), aluno.getNome(), aluno.getEmail().getEmailAddress(), cursos);
    }

    @GetMapping("/{id}")
    public AlunoDTO getAluno(@PathVariable Long id) {
        Aluno aluno = alunoService.buscarAluno(id);
        if (aluno == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado com id: " + id);
        }
        return converterParaDTO(aluno);
    }

    @GetMapping
    public List<AlunoDTO> getAllAlunos() {
        List<Aluno> alunos = alunoService.listarTodosAlunos();
        return alunos.stream()
                     .map(this::converterParaDTO)
                     .collect(Collectors.toList());
    }
}
