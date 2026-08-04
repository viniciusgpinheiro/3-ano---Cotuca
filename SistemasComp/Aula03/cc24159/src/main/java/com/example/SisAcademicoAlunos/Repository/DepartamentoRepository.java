package com.example.SisAcademicoAlunos.Repository;

import com.example.SisAcademicoAlunos.Model.Aluno;
import com.example.SisAcademicoAlunos.Model.Departamento;
import com.example.SisAcademicoAlunos.Model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {
}
