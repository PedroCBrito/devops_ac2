package com.tdd.ac2.service.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.tdd.ac2.entity.Aluno;
import com.tdd.ac2.repository.AlunoRepository;
import com.tdd.ac2.service.AlunoService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @InjectMocks
    private AlunoService alunoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void buscarAluno_DeveRetornarAluno_QuandoIdExistir() {
        // Arrange
        Long alunoId = 1L;
        Aluno alunoMock = new Aluno("Carlos Silva", "carlos@exemplo.com");
        alunoMock.setId(alunoId);
        when(alunoRepository.findById(alunoId)).thenReturn(Optional.of(alunoMock));

        // Act
        Aluno alunoEncontrado = alunoService.buscarAluno(alunoId);

        // Assert
        assertNotNull(alunoEncontrado);
        assertEquals(alunoMock.getId(), alunoEncontrado.getId());
        assertEquals(alunoMock.getNome(), alunoEncontrado.getNome());
        verify(alunoRepository, times(1)).findById(alunoId);
    }

    @Test
    void buscarAluno_DeveRetornarNull_QuandoIdNaoExistir() {
        // Arrange
        Long alunoId = 1L;
        when(alunoRepository.findById(alunoId)).thenReturn(Optional.empty());

        // Act
        Aluno alunoEncontrado = alunoService.buscarAluno(alunoId);

        // Assert
        assertNull(alunoEncontrado);
        verify(alunoRepository, times(1)).findById(alunoId);
    }

    @Test
    void listarTodosAlunos_DeveRetornarListaDeAlunos() {
        // Arrange
        Aluno aluno1 = new Aluno("Carlos Silva", "carlos@exemplo.com");
        Aluno aluno2 = new Aluno("Maria Souza", "maria@exemplo.com");
        List<Aluno> alunosMock = Arrays.asList(aluno1, aluno2);
        when(alunoRepository.findAll()).thenReturn(alunosMock);

        // Act
        List<Aluno> alunosEncontrados = alunoService.listarTodosAlunos();

        // Assert
        assertNotNull(alunosEncontrados);
        assertEquals(2, alunosEncontrados.size());
        assertEquals(aluno1.getNome(), alunosEncontrados.get(0).getNome());
        assertEquals(aluno2.getNome(), alunosEncontrados.get(1).getNome());
        verify(alunoRepository, times(1)).findAll();
    }
}
