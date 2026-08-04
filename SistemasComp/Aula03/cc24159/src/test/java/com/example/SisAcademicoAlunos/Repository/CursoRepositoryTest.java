package com.example.SisAcademicoAlunos.Repository;

import com.example.SisAcademicoAlunos.Model.Curso;
import com.example.SisAcademicoAlunos.Model.Departamento;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CursoRepositoryTest {
    @Autowired
    CursoRepository repository;

    @Autowired
    DepartamentoRepository departamentoRepository;

    @Test
    public void incluir() {
        Departamento departamento1 = departamentoRepository.findById(1).orElse(null);
        Departamento departamento2 = departamentoRepository.findById(2).orElse(null);

        Curso curso1 = new Curso();
        curso1.setNome("Desenvolvimentos de jogos");
        curso1.setCodigo("DJ123");
        curso1.setCargaHoraria(1200);
        curso1.setDepartamento(departamento1);

        Curso curso2 = new Curso();
        curso2.setNome("Programacao Web");
        curso2.setCodigo("PW321");
        curso2.setCargaHoraria(1000);
        curso2.setDepartamento(departamento1);

        Curso curso3 = new Curso();
        curso3.setNome("Neurociência");
        curso3.setCodigo("NC123");
        curso3.setCargaHoraria(1500);
        curso3.setDepartamento(departamento2);

        repository.saveAll(List.of(curso1, curso2, curso3));
        System.out.println("Novos cursos incluidos");
    }
}