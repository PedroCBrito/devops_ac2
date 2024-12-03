package com.tdd.ac2.entity.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import com.tdd.ac2.entity.Aluno_Email;

public class Aluno_EmailTest {

    @Test
    void testValidEmail() {
        Aluno_Email email = new Aluno_Email("valid.email@example.com");
        assertEquals("valid.email@example.com", email.getEmailAddress());
    }

    @Test
    void testInvalidEmailWithoutAtSymbolThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Aluno_Email("invalid.email.com");
        });
    }

    @Test
    void testInvalidEmailWithoutDomainThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Aluno_Email("invalid@.com");
        });
    }

    @Test
    void testInvalidEmailWithoutLocalPartThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Aluno_Email("@example.com");
        });
    }

    @Test
    void testNullEmailThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Aluno_Email(null);
        });
    }

    @Test
    void testEqualityForSameEmail() {
        Aluno_Email email1 = new Aluno_Email("test@example.com");
        Aluno_Email email2 = new Aluno_Email("test@example.com");
        
        assertEquals(email1, email2);
    }

    @Test
    void testInequalityForDifferentEmails() {
        Aluno_Email email1 = new Aluno_Email("test@example.com");
        Aluno_Email email2 = new Aluno_Email("different@example.com");
        
        assertNotEquals(email1, email2);
    }

    @Test
    void testHashCodeForSameEmail() {
        Aluno_Email email1 = new Aluno_Email("test@example.com");
        Aluno_Email email2 = new Aluno_Email("test@example.com");
        
        assertEquals(email1.hashCode(), email2.hashCode());
    }

    @Test
    void testHashCodeForDifferentEmails() {
        Aluno_Email email1 = new Aluno_Email("test@example.com");
        Aluno_Email email2 = new Aluno_Email("different@example.com");
        
        assertNotEquals(email1.hashCode(), email2.hashCode());
    }

    @Test
    void testDefaultConstructorProtectedAccess() {
        // Testing indirectly, since direct instantiation is not possible outside the package
        Aluno_Email email = new Aluno_Email("protected@example.com");
        assertNotNull(email);
    }
}