package com.tdd.ac2.entity.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tdd.ac2.entity.Aluno;
import com.tdd.ac2.entity.Curso;
import com.tdd.ac2.entity.Forum;

import java.util.*;

public class ForumTest {

    private Forum forum;
    private Aluno aluno1;
    private Aluno aluno2;
    private Curso curso;

    @BeforeEach
    void setUp() {
        forum = new Forum();
        aluno1 = mock(Aluno.class);
        aluno2 = mock(Aluno.class);
        curso = new Curso("Matemática");
    }
    
    @Test
    void testAddComentario() {
        // Arrange
        Forum forum = new Forum();
        Aluno aluno = new Aluno("João", "joao@email.com"); // Ajuste conforme o construtor de Aluno
        String comentario1 = "Primeiro comentário";
        String comentario2 = "Segundo comentário";

        // Act
        forum.addComentario(aluno, comentario1);
        forum.addComentario(aluno, comentario2);

        // Assert
        // Verifica se os comentários do aluno João estão sendo retornados corretamente
        String[] comentarios = forum.getComentarios(aluno); // Ajuste para chamar o método passando o aluno
        assertNotNull(comentarios, "Os comentários não devem ser nulos.");
        assertEquals(2, comentarios.length, "O aluno deve ter 2 comentários.");
        assertArrayEquals(
            new String[]{comentario1, comentario2}, 
            comentarios, 
            "Os comentários do aluno devem ser adicionados corretamente."
        );
    }

     @Test
    void testAddTopico() {
        forum.addTopico(aluno1, "Primeiro tópico");
        forum.addTopico(aluno2, "Segundo tópico");

        List<Aluno> alunosComTopicos = forum.verificarAlunoComMaisComentariosETopicosDoForum();

        assertEquals(2, alunosComTopicos.size());
        assertTrue(alunosComTopicos.contains(aluno1));
        assertTrue(alunosComTopicos.contains(aluno2));
    }

    @Test
    void testVerificarAlunoComMaisComentariosETopicosDoForum() {
        forum.addComentario(aluno1, "Comentário 1");
        forum.addTopico(aluno1, "Tópico 1");
        forum.addTopico(aluno2, "Tópico 2");

        List<Aluno> alunosMaisAtivos = forum.verificarAlunoComMaisComentariosETopicosDoForum();

        assertEquals(1, alunosMaisAtivos.size());
        assertEquals(aluno1, alunosMaisAtivos.get(0));
    }

    @Test
    void testVerificaFimDeMesEaddCursoComDataCorreta() {
        forum.addComentario(aluno1, "Comentário relevante");
        forum.addTopico(aluno1, "Tópico interessante");

        when(aluno1.getCursos()).thenReturn(new ArrayList<>());

        forum.verificaFimDeMesEaddCurso("01-12-24", curso);

        verify(aluno1).addCurso(curso);
    }

    @Test
    void testVerificaFimDeMesEaddCursoComDataIncorreta() {
        forum.addComentario(aluno1, "Comentário 1");
        forum.addTopico(aluno1, "Tópico 1");

        forum.verificaFimDeMesEaddCurso("15-12-24", curso);

        verify(aluno1, never()).addCurso(curso);
    }

    @Test
    void testVerificaFimDeMesEaddCursoComDataInvalida() {
        forum.addComentario(aluno1, "Comentário");
        forum.addTopico(aluno1, "Tópico");

        assertDoesNotThrow(() -> forum.verificaFimDeMesEaddCurso("data-invalida", curso));

        verify(aluno1, never()).addCurso(curso);
    }
}
