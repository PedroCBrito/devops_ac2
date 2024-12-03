package com.tdd.ac2.DTO.test;

import org.junit.jupiter.api.Test;
import com.tdd.ac2.dto.AlunoDTO;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

public class AlunoDTOTest {

    @Test
    public void testConstructorAndGetters() {
        // Criando uma lista de cursos
        List<String> cursos = Arrays.asList("Matemática", "Programação");

        // Instanciando um objeto AlunoDTO
        AlunoDTO aluno = new AlunoDTO(1L, "João", "joao@example.com", cursos);

        // Testando os getters
        assertEquals(1L, aluno.getId());
        assertEquals("João", aluno.getNome());
        assertEquals("joao@example.com", aluno.getEmail());
        assertEquals(cursos, aluno.getCursos());
    }

    @Test
    public void testSetters() {
        // Criando um objeto AlunoDTO
        AlunoDTO aluno = new AlunoDTO(1L, "João", "joao@example.com", Arrays.asList("Matemática"));

        // Usando os setters para modificar os valores
        aluno.setId(2L);
        aluno.setNome("Maria");
        aluno.setEmail("maria@example.com");
        aluno.setCursos(Arrays.asList("Física", "Química"));

        // Testando os setters
        assertEquals(2L, aluno.getId());
        assertEquals("Maria", aluno.getNome());
        assertEquals("maria@example.com", aluno.getEmail());
        assertEquals(Arrays.asList("Física", "Química"), aluno.getCursos());
    }

    @Test
    public void testEmptyCourses() {
        // Criando um AlunoDTO com uma lista de cursos vazia
        AlunoDTO aluno = new AlunoDTO(3L, "Carlos", "carlos@example.com", Arrays.asList());

        // Verificando se a lista de cursos está vazia
        assertTrue(aluno.getCursos().isEmpty());
    }
}
