package com.tdd.ac2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tdd.ac2.entity.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
