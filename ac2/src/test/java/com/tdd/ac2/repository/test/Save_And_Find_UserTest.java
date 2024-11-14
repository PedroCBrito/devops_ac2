package com.tdd.ac2.repository.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.tdd.ac2.entity.Aluno;
import com.tdd.ac2.repository.AlunoRepository;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class Save_And_Find_UserTest {

    @Autowired
    private AlunoRepository alunoRepository;

    @Test
    public void testSaveAndFindAluno() {
        // Cria um novo aluno
        Aluno aluno = new Aluno("NomeTeste", "teste@exemplo.com");

        // Salva no banco de dados
        Aluno savedAluno = alunoRepository.save(aluno);
        assertNotNull(savedAluno.getId()); // Verifica se o ID foi gerado

        // Busca o aluno pelo ID
        Optional<Aluno> retrievedAluno = alunoRepository.findById(savedAluno.getId());
        assertThat(retrievedAluno).isPresent();
        assertThat(retrievedAluno.get().getNome()).isEqualTo("NomeTeste");
    }
}

