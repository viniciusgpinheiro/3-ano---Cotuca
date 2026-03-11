package com.example.SisAcademicoAlunos.Repository;

import com.example.SisAcademicoAlunos.Model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
}
