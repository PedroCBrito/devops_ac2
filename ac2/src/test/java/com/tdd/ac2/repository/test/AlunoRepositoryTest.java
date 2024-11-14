package com.tdd.ac2.repository.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.tdd.ac2.entity.Aluno;
import com.tdd.ac2.repository.AlunoRepository;


@ActiveProfiles("test")
@SpringBootTest
public class AlunoRepositoryTest {

    @Autowired
    private AlunoRepository alunoRepository;

    @Test
    void testSaveAndFindAluno() {
        // Cria um objeto Aluno com um email válido
        Aluno aluno = new Aluno("NomeTeste", "teste@exemplo.com");

        // Salva no banco de dados
        Aluno savedAluno = alunoRepository.save(aluno);
        assertNotNull(savedAluno.getId()); // Verifica se o ID foi gerado

        // Busca o aluno pelo ID
        Optional<Aluno> retrievedAluno = alunoRepository.findById(savedAluno.getId());
        assertTrue(retrievedAluno.isPresent());
        assertEquals("NomeTeste", retrievedAluno.get().getNome());
    }
}
