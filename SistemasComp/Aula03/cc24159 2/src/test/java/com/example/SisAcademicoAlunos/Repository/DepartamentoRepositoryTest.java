package com.example.SisAcademicoAlunos.Repository;

import com.example.SisAcademicoAlunos.Model.Aluno;
import com.example.SisAcademicoAlunos.Model.Departamento;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartamentoRepositoryTest {
    @Autowired
    DepartamentoRepository repository;

    @Test
    public void incluir() {
        Departamento departamento1 = new Departamento();
        departamento1.setNome("Informática");

        Departamento departamento2 = new Departamento();
        departamento2.setNome("Enfermagem");

        Departamento departamento3 = new Departamento();
        departamento3.setNome("Alimentos");

        repository.saveAll(List.of(departamento1, departamento2, departamento3));
        System.out.println("Novos departamentos incluidos");
    }
}