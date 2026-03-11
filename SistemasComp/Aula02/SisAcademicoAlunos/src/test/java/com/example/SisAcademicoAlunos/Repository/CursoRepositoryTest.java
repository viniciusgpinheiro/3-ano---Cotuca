package com.example.SisAcademicoAlunos.Repository;

import com.example.SisAcademicoAlunos.Model.Curso;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CursoRepositoryTest {
    @Autowired
    CursoRepository repository;

    @Test
    public void incluirDadosCurso() {
        Curso curso = new Curso();
        curso.setCodigo("DC999");
        curso.setNome("Informatica");
        curso.setCargaHoraria(1225);
        var cursoSalvo = repository.save(curso);
        System.out.println("Curso salvo: " + cursoSalvo);
    }

    @Test
    public void listarTodosRegistrosCurso() {
        List<Curso> listaCurso = repository.findAll();
        System.out.println("Registros de cursos: ");
        listaCurso.forEach(System.out::println);
    }

    @Test
    public void contarRegistrosCurso() {
        System.out.println("Quantos registros: "+ repository.count());
    }

    @Test
    public void deletarRegistroCurso() {
        var id = 2;
        repository.deleteById(id);
        System.out.println("Apagou o id = " + id);
    }
}
