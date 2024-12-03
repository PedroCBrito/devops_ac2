package com.tdd.ac2.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Forum {
    
    private HashMap<Aluno, String[]> Comentarios;
    private HashMap<Aluno, String[]> Topicos;

    public Forum() {
        Comentarios = new HashMap<>();
        Topicos = new HashMap<>();
    }
    
    public void addComentario(Aluno aluno, String comentario) {
        Comentarios.merge(aluno, new String[]{comentario}, (existentes, novos) -> {
            String[] atualizados = Arrays.copyOf(existentes, existentes.length + 1);
            atualizados[existentes.length] = novos[0];
            return atualizados;
        });
    }

	
    // Método para adicionar tópico
    public void addTopico(Aluno aluno, String topico) {
        if (Topicos.containsKey(aluno)) {
            String[] topicosExistentes = Topicos.get(aluno);
            String[] novoTopico = Arrays.copyOf(topicosExistentes, topicosExistentes.length + 1);
            
            novoTopico[novoTopico.length - 1] = topico;
            
            Topicos.put(aluno, novoTopico);
        } else {
            Topicos.put(aluno, new String[]{topico});
        }
    }

 // Função para verificar os alunos com mais comentários e tópicos somados
    public List<Aluno> verificarAlunoComMaisComentariosETopicosDoForum() {
        // Soma os comentários e tópicos para cada aluno
        Map<Aluno, Integer> interacoesPorAluno = new HashMap<>();
        Comentarios.forEach((aluno, comentarios) -> 
            interacoesPorAluno.merge(aluno, comentarios.length, Integer::sum));
        Topicos.forEach((aluno, topicos) -> 
            interacoesPorAluno.merge(aluno, topicos.length, Integer::sum));

        // Identifica o maior número de interações
        int maxInteracoes = interacoesPorAluno.values().stream()
                .max(Integer::compare)
                .orElse(0);

        // Filtra os alunos com o maior número de interações
        return interacoesPorAluno.entrySet().stream()
                .filter(entry -> entry.getValue() == maxInteracoes)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }


	public void verificaFimDeMesEaddCurso(String string, Curso novoCurso) {
		// Formato esperado: "dd-mm-yy"
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
        sdf.setLenient(false);  // Desabilitar interpretação de datas inválidas

        try {
            // Tentar converter a string para uma data
            Date data = sdf.parse(string);

            // Verificar se o dia é o primeiro do mês
            if (data.getDate() == 1) {
            	// Chamar a função verificarAlunoComMaisComentariosETopicosDoForum
                List<Aluno> alunosComMaisInteracoes = verificarAlunoComMaisComentariosETopicosDoForum();
                
                // Adicionar o curso para todos os alunos da lista
                for (Aluno aluno : alunosComMaisInteracoes) {
                    aluno.addCurso(novoCurso);
                }
                
            } else {
                // Não é o primeiro dia do mês
                return;
            }
        } catch (ParseException e) {
            // Se a data não estiver no formato correto
            return;
        }
		
	}

	public String[] getComentarios(Aluno aluno) {
	    // Retorna os comentários do aluno, ou um array vazio se o aluno não tiver comentários
	    return Comentarios.getOrDefault(aluno, new String[]{});
	}

}
