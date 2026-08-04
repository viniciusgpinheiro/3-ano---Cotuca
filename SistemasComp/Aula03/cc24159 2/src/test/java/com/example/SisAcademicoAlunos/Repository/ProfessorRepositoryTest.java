package com.example.SisAcademicoAlunos.Repository;

import com.example.SisAcademicoAlunos.Model.Curso;
import com.example.SisAcademicoAlunos.Model.Departamento;
import com.example.SisAcademicoAlunos.Model.Matricula;
import com.example.SisAcademicoAlunos.Model.Professor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProfessorRepositoryTest {
    @Autowired
    ProfessorRepository repository;

    @Autowired
    DepartamentoRepository departamentoRepository;

    @Autowired
    CursoRepository cursoRepository;

    @Test
    public void incluir() {

        Departamento departamento1 = departamentoRepository.findById(1).orElse(null);
        Departamento departamento2 = departamentoRepository.findById(2).orElse(null);

        Professor professor1 = new Professor();
        professor1.setNome("Chico");
        professor1.setCelular(1999991111);
        professor1.setEmail("chico@gmail.com");
        professor1.setDataNascimento(LocalDate.now());
        professor1.setMatricula(13579);
        professor1.setDepartamento(departamento1);

        Professor professor2 = new Professor();
        professor2.setNome("Andreia");
        professor2.setCelular(1977771111);
        professor2.setEmail("andreia@gmail.com");
        professor2.setDataNascimento(LocalDate.now());
        professor2.setMatricula(24680);
        professor2.setDepartamento(departamento1);

        Professor professor3 = new Professor();
        professor3.setNome("Patricia");
        professor3.setCelular(1911114444);
        professor3.setEmail("patricia@gmail.com");
        professor3.setDataNascimento(LocalDate.now());
        professor3.setMatricula(12560);
        professor3.setDepartamento(departamento2);

        repository.saveAll(List.of(professor1, professor2, professor3));
    }

    @Test
    public void listarProfessoresDeCurso() {
        var idCurso = 1;
        Curso curso = cursoRepository.findById(idCurso).orElse(null);
        Departamento departamento = curso.getDepartamento();
        List<Professor> listaProfessores = repository.findByDepartamentoId(departamento.getId());

        System.out.printf("Registros de professores do curso: %s \n", curso.getNome());
        listaProfessores.forEach(System.out::println);
    }

    @Test
    public void listarDiciplinasProfessor() {
        var idProfessor = 1;
        Professor professor = repository.findById(idProfessor).orElse(null);
        Integer idDepartamento = professor.getDepartamento().getId();
        List<Curso> cursos = cursoRepository.findByDepartamentoId(idDepartamento);
        cursos.forEach(System.out::println);
    }

    @Test
    public void listarProfessoresDepartamento() {
        var idDepartamento = 1;
        List<Professor> listaProfessores = repository.findByDepartamentoId(idDepartamento);
        listaProfessores.forEach(System.out::println);
    }
}