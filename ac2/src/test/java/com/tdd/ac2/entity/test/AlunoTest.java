package com.tdd.ac2.entity.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.tdd.ac2.entity.Aluno;
import com.tdd.ac2.entity.Aluno_Email;

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
}
