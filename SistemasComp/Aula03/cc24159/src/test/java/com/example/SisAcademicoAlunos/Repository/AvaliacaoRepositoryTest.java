package com.example.SisAcademicoAlunos.Repository;

import com.example.SisAcademicoAlunos.Model.Aluno;
import com.example.SisAcademicoAlunos.Model.Avaliacao;
import com.example.SisAcademicoAlunos.Model.Diciplina;
import com.example.SisAcademicoAlunos.Model.Professor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AvaliacaoRepositoryTest {
    @Autowired
    AvaliacaoRepository repository;

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    DiciplinaRepository diciplinaRepository;

    @Autowired
    ProfessorRepository professorRepository;

    @Test
    public void incluir() {
        Diciplina diciplina1 = diciplinaRepository.findById(1).orElse(null);
        Aluno aluno1 = alunoRepository.findById(1).orElse(null);
        Aluno aluno2 = alunoRepository.findById(2).orElse(null);
        Aluno aluno3 = alunoRepository.findById(3).orElse(null);
        Professor professor1 = professorRepository.findById(1).orElse(null);

        Avaliacao av1 = new Avaliacao();
        av1.setNome("AV1");
        av1.setData(LocalDate.now());
        av1.setNota(10.0);
        av1.setDiciplinas(diciplina1);
        av1.setAluno(aluno1);
        av1.setProfessor(professor1);

        Avaliacao av2 = new Avaliacao();
        av2.setNome("AV1");
        av2.setData(LocalDate.now());
        av2.setNota(5.0);
        av2.setDiciplinas(diciplina1);
        av2.setAluno(aluno2);
        av2.setProfessor(professor1);

        Avaliacao av3 = new Avaliacao();
        av3.setNome("AV1");
        av3.setData(LocalDate.now());
        av3.setNota(1.0);
        av3.setDiciplinas(diciplina1);
        av3.setAluno(aluno3);
        av3.setProfessor(professor1);

        repository.saveAll(List.of(av1, av2, av3));
    }
}