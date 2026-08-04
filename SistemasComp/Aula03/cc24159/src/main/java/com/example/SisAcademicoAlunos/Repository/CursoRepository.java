package com.example.SisAcademicoAlunos.Repository;

import com.example.SisAcademicoAlunos.Model.Curso;
import com.example.SisAcademicoAlunos.Model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
    List<Curso> findByDepartamentoId(Integer idDepartamento);
}
