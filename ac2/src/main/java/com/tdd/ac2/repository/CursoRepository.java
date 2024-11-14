package com.tdd.ac2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tdd.ac2.entity.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    // Métodos personalizados podem ser adicionados aqui, se necessário
}
