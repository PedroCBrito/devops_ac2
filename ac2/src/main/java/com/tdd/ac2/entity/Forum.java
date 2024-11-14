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
        // Verifica se o aluno já comentou antes
        if (Comentarios.containsKey(aluno)) {
            String[] comentariosExistentes = Comentarios.get(aluno);
            String[] novoComentarios = Arrays.copyOf(comentariosExistentes, comentariosExistentes.length + 1);
       
            novoComentarios[novoComentarios.length - 1] = comentario;
           
            Comentarios.put(aluno, novoComentarios);
        } else {
            
            Comentarios.put(aluno, new String[]{comentario});
        }
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
        Map<Aluno, Integer> interacoesPorAluno = new HashMap<>();
        
        // Contabiliza interações dos alunos que possuem comentários
        for (Map.Entry<Aluno, String[]> entry : Comentarios.entrySet()) {
            Aluno aluno = entry.getKey();
            int numComentarios = entry.getValue().length;
            int numTopicos = Topicos.getOrDefault(aluno, new String[0]).length;  // Tópicos do aluno
            interacoesPorAluno.put(aluno, numComentarios + numTopicos);
        }

        // Contabiliza interações dos alunos que possuem apenas tópicos
        for (Map.Entry<Aluno, String[]> entry : Topicos.entrySet()) {
            Aluno aluno = entry.getKey();
            if (!interacoesPorAluno.containsKey(aluno)) {  // Se ainda não foi contabilizado
                interacoesPorAluno.put(aluno, entry.getValue().length);
            }
        }

        // Verifica o maior número de interações
        int maxInteracoes = interacoesPorAluno.values().stream().max(Integer::compare).orElse(0);

        // Retorna a lista dos alunos com o maior número de interações
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



}
