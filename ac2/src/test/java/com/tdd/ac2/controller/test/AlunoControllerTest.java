package com.tdd.ac2.controller.test;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.tdd.ac2.controller.AlunoController;
import com.tdd.ac2.dto.AlunoDTO;
import com.tdd.ac2.entity.Aluno;
import com.tdd.ac2.entity.Curso;
import com.tdd.ac2.service.AlunoService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AlunoController.class)
public class AlunoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlunoService alunoService;

    private Aluno criarAlunoMock() {
        Aluno aluno = new Aluno("Maria Silva", "maria@example.com");
        aluno.setId(1L);
        aluno.addCurso(new Curso("Matemática"));
        aluno.addCurso(new Curso("Física"));
        return aluno;
    }

    private AlunoDTO converterParaDTO(Aluno aluno) {
        return new AlunoDTO(
            aluno.getId(),
            aluno.getNome(),
            aluno.getEmail().getEmailAddress(),
            aluno.getCursos().stream().map(Curso::getNome).collect(Collectors.toList())
        );
    }

    @Test
    public void testGetAllAlunos() throws Exception {
        Aluno alunoMock = criarAlunoMock();

        Mockito.when(alunoService.listarTodosAlunos()).thenReturn(List.of(alunoMock));

        mockMvc.perform(MockMvcRequestBuilders.get("/alunos")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Maria Silva"))
                .andExpect(jsonPath("$[0].email").value("maria@example.com"))
                .andExpect(jsonPath("$[0].cursos[0]").value("Matemática"))
                .andExpect(jsonPath("$[0].cursos[1]").value("Física"));
    }

    @Test
    public void testGetAlunoById() throws Exception {
        Aluno alunoMock = criarAlunoMock();

        Mockito.when(alunoService.buscarAluno(1L)).thenReturn(alunoMock);

        mockMvc.perform(MockMvcRequestBuilders.get("/alunos/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Maria Silva"))
                .andExpect(jsonPath("$.email").value("maria@example.com"))
                .andExpect(jsonPath("$.cursos[0]").value("Matemática"))
                .andExpect(jsonPath("$.cursos[1]").value("Física"));
    }

    @Test
    public void testGetAllAlunosMultiple() throws Exception {
        Aluno aluno1 = criarAlunoMock();
        Aluno aluno2 = new Aluno("João Souza", "joao@example.com");
        aluno2.setId(2L);
        aluno2.addCurso(new Curso("Química"));
        aluno2.addCurso(new Curso("Biologia"));

        Mockito.when(alunoService.listarTodosAlunos()).thenReturn(List.of(aluno1, aluno2));

        mockMvc.perform(MockMvcRequestBuilders.get("/alunos")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Maria Silva"))
                .andExpect(jsonPath("$[1].nome").value("João Souza"))
                .andExpect(jsonPath("$[1].email").value("joao@example.com"))
                .andExpect(jsonPath("$[1].cursos[0]").value("Química"))
                .andExpect(jsonPath("$[1].cursos[1]").value("Biologia"));
    }

}
