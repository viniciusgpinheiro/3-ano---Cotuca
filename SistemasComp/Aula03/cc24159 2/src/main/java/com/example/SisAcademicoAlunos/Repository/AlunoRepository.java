package com.example.SisAcademicoAlunos.Repository;

import com.example.SisAcademicoAlunos.Model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
}
