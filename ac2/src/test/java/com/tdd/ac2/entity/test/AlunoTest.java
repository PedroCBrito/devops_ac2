package com.tdd.ac2.entity.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.tdd.ac2.entity.Aluno;
import com.tdd.ac2.entity.Aluno_Email;
import com.tdd.ac2.entity.Curso;

public class AlunoTest {

    @Test
    void testSetAndGetValidEmail() {
        Aluno aluno = new Aluno("João", "joao@example.com");
        Aluno_Email email = new Aluno_Email("joao@example.com");
        aluno.setEmail(email);

        assertEquals(email, aluno.getEmail());
    }

    @Test
    void testInvalidEmailThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Aluno_Email("invalid-email");
        });
    }

    @Test
    void testChangeEmail() {
        Aluno aluno = new Aluno("Carlos", "carlos@example.com");
        Aluno_Email oldEmail = new Aluno_Email("carlos@example.com");
        aluno.setEmail(oldEmail);

        Aluno_Email newEmail = new Aluno_Email("carlos.new@example.com");
        aluno.setEmail(newEmail);

        assertEquals(newEmail, aluno.getEmail());
    }

    @Test
    void testAddCurso() {
        Aluno aluno = new Aluno("Ana", "ana@example.com");
        Curso curso = new Curso("Matemática");

        aluno.addCurso(curso);

        assertTrue(aluno.getCursos().contains(curso));
    }

    @Test
    void testHasCurso() {
        Aluno aluno = new Aluno("Lucas", "lucas@example.com");
        Curso curso = new Curso("História");

        aluno.addCurso(curso);

        assertTrue(aluno.temCurso(curso));
    }

    @Test
    void testDoesNotHaveCurso() {
        Aluno aluno = new Aluno("Mariana", "mariana@example.com");
        Curso curso = new Curso("Física");

        assertFalse(aluno.temCurso(curso));
    }

    @Test
    void testGetCursos() {
        Aluno aluno = new Aluno("João", "joao@example.com");
        Curso curso1 = new Curso("Química");
        Curso curso2 = new Curso("Biologia");

        aluno.addCurso(curso1);
        aluno.addCurso(curso2);

        List<Curso> cursos = aluno.getCursos();

        assertEquals(2, cursos.size());
        assertTrue(cursos.contains(curso1));
        assertTrue(cursos.contains(curso2));
    }

    @Test
    void testSetAndGetNome() {
        Aluno aluno = new Aluno("João", "joao@example.com");
        aluno.setNome("João Silva");

        assertEquals("João Silva", aluno.getNome());
    }

    @Test
    void testSetAndGetId() {
        Aluno aluno = new Aluno();
        aluno.setId(1L);

        assertEquals(1L, aluno.getId());
    }

    @Test
    void testDefaultConstructor() {
        Aluno aluno = new Aluno();

        assertNull(aluno.getId());
        assertNull(aluno.getNome());
        assertNull(aluno.getEmail());
        assertTrue(aluno.getCursos().isEmpty());
    }

    @Test
    void testParameterizedConstructor() {
        Aluno aluno = new Aluno("Pedro", "pedro@example.com");

        assertEquals("Pedro", aluno.getNome());
        assertEquals(new Aluno_Email("pedro@example.com"), aluno.getEmail());
        assertTrue(aluno.getCursos().isEmpty());
    }
}