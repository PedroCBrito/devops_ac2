package com.tdd.ac2.entity;

import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class Aluno_Email {

    private String emailAddress;

    // Construtor padrão necessário para o JPA
    protected Aluno_Email() {}

    public Aluno_Email(String emailAddress) {
        if (emailAddress == null || !emailAddress.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("Email inválido");
        }
        this.emailAddress = emailAddress;
    }


    public String getEmailAddress() {
        return emailAddress;
    }

    // Sobrescreva equals e hashCode para garantir comparação por valor
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno_Email email = (Aluno_Email) o;
        return Objects.equals(emailAddress, email.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailAddress);
    }

	
}