package com.example.SisAcademicoAlunos.Repository;

import com.example.SisAcademicoAlunos.Model.Aluno;
import com.example.SisAcademicoAlunos.Model.Curso;
import com.example.SisAcademicoAlunos.Model.Matricula;
import jakarta.transaction.Transactional;
import net.bytebuddy.asm.Advice;
import org.hibernate.sql.model.PreparableMutationOperation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MatriculaRepositoryTest {
    @Autowired
    MatriculaRepository repository;

    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    AlunoRepository alunoRepository;

    @Test
    public void incluir()
    {
        Curso curso1 = cursoRepository.findById(1).orElse(null);

        Matricula matricula1 = new Matricula();
        matricula1.setCodigo("12345");
        matricula1.setHora(LocalTime.now());
        matricula1.setData(LocalDate.now());
        matricula1.setCurso(curso1);

        Matricula matricula2 = new Matricula();
        matricula2.setCodigo("54321");
        matricula2.setHora(LocalTime.now());
        matricula2.setData(LocalDate.now());
        matricula2.setCurso(curso1);

        Matricula matricula3 = new Matricula();
        matricula3.setCodigo("67890");
        matricula3.setHora(LocalTime.now());
        matricula3.setData(LocalDate.now());
        matricula3.setCurso(curso1);

        repository.saveAll(List.of(matricula1, matricula2, matricula3));
    }

    @Test
    public void atualizar() {
        Curso curso3 = cursoRepository.findById(3).orElse(null);
        Matricula matricula2 = repository.findById(3).orElse(null);
        matricula2.setCurso(curso3);
        matricula2.setCodigo("33311");
        matricula2.setHora(LocalTime.now());
        matricula2.setData(LocalDate.now());
        repository.save(matricula2);
    }

    @Test
    public void listarCru() {
        List<Matricula> listaMatricula = repository.findAll();
        System.out.println("Registros de matriculas: ");
        listaMatricula.forEach(System.out::println);
    }

    @Test
    public void excluir() {
        var id = 1;
        repository.deleteById(id);
        System.out.println("Apagou a matrícula com id = " + id);
    }

    @Test
    public void listarComDados() {
        Matricula matricula = repository.findById(3).orElse(null);
        System.out.printf( "* código da matrícula:  %s\n" +
                            "* data da matrícula:   %s\n" +
                            "* nome do curso:       %s\n" +
                            "* ra do aluno:         %s\n" +
                            "* nome do aluno:       %s\n",
                matricula.getCodigo(),
                matricula.getData(),
                matricula.getCurso().getNome(),
                matricula.getAluno().getRa(),
                matricula.getAluno().getNome()
                );
    }
}