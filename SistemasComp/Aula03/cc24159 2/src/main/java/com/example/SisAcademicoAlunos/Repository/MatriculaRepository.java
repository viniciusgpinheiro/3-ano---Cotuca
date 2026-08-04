package com.example.SisAcademicoAlunos.Repository;

import com.example.SisAcademicoAlunos.Model.Aluno;
import com.example.SisAcademicoAlunos.Model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {
}
