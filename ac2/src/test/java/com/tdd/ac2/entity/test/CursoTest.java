package com.tdd.ac2.entity.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import com.tdd.ac2.entity.Curso;

public class CursoTest {

    @Test
    void testDefaultConstructor() {
        Curso curso = new Curso();

        assertNull(curso.getId());
        assertNull(curso.getNome());
    }

    @Test
    void testParameterizedConstructor() {
        Curso curso = new Curso("Matemática");

        assertEquals("Matemática", curso.getNome());
        assertNull(curso.getId()); // ID should be null until persisted
    }

    @Test
    void testSetAndGetNome() {
        Curso curso = new Curso();
        curso.setNome("Física");

        assertEquals("Física", curso.getNome());
    }

    @Test
    void testSetAndGetId() {
        Curso curso = new Curso();
        curso.setId(1L);

        assertEquals(1L, curso.getId());
    }

    @Test
    void testEqualityForSameIdAndNome() {
        Curso curso1 = new Curso("Química");
        curso1.setId(1L);

        Curso curso2 = new Curso("Química");
        curso2.setId(1L);

        assertEquals(curso1, curso2);
    }

    @Test
    void testInequalityForDifferentIds() {
        Curso curso1 = new Curso("História");
        curso1.setId(1L);

        Curso curso2 = new Curso("História");
        curso2.setId(2L);

        assertNotEquals(curso1, curso2);
    }

    @Test
    void testInequalityForDifferentNomes() {
        Curso curso1 = new Curso("Geografia");
        curso1.setId(1L);

        Curso curso2 = new Curso("Artes");
        curso2.setId(1L);

        assertNotEquals(curso1, curso2);
    }

    @Test
    void testHashCodeForSameIdAndNome() {
        Curso curso1 = new Curso("Biologia");
        curso1.setId(1L);

        Curso curso2 = new Curso("Biologia");
        curso2.setId(1L);

        assertEquals(curso1.hashCode(), curso2.hashCode());
    }

    @Test
    void testHashCodeForDifferentIds() {
        Curso curso1 = new Curso("Filosofia");
        curso1.setId(1L);

        Curso curso2 = new Curso("Filosofia");
        curso2.setId(2L);

        assertNotEquals(curso1.hashCode(), curso2.hashCode());
    }

    @Test
    void testHashCodeForDifferentNomes() {
        Curso curso1 = new Curso("Literatura");
        curso1.setId(1L);

        Curso curso2 = new Curso("Gramática");
        curso2.setId(1L);

        assertNotEquals(curso1.hashCode(), curso2.hashCode());
    }
}