package com.example.SisAcademicoAlunos.Repository;

import com.example.SisAcademicoAlunos.Model.Aluno;
import com.example.SisAcademicoAlunos.Model.Matricula;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AlunoRepositoryTest {
    @Autowired
    AlunoRepository repository;

    @Autowired
    MatriculaRepository matriculaRepository;

    @Test
    public void incluir() {
        Matricula matricula1 = matriculaRepository.findById(1).orElse(null);
        Matricula matricula2 = matriculaRepository.findById(2).orElse(null);
        Matricula matricula3 = matriculaRepository.findById(3).orElse(null);

        Aluno aluno1 = new Aluno();
        aluno1.setRa("24159");
        aluno1.setNome("Vinicius Pinheiro");
        aluno1.setEmail("cc24159@g.unicamp.br");
        aluno1.setMatricula(matricula1);

        Aluno aluno2 = new Aluno();
        aluno2.setRa("24130");
        aluno2.setNome("Guilherme Profeta");
        aluno2.setEmail("cc24130@g.unicamp.br");
        aluno2.setMatricula(matricula2);

        Aluno aluno3 = new Aluno();
        aluno3.setRa("24151");
        aluno3.setNome("Rafael Vasconsellos");
        aluno3.setEmail("cc24151@g.unicamp.br");
        aluno3.setMatricula(matricula3);

        repository.saveAll(List.of(aluno1, aluno2, aluno3));
    }
}