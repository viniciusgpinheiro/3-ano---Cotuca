package com.example.SisAcademicoAlunos.Repository;

import com.example.SisAcademicoAlunos.Model.Curso;
import com.example.SisAcademicoAlunos.Model.Departamento;
import com.example.SisAcademicoAlunos.Model.Diciplina;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DiciplinaRepositoryTest {
    @Autowired
    DiciplinaRepository repository;

    @Autowired
    CursoRepository cursoRepository;

    @Test
    public void incluir() {
        Curso curso1 = cursoRepository.findById(1).orElse(null);
        Curso curso2 = cursoRepository.findById(2).orElse(null);

        Diciplina diciplina1 = new Diciplina();
        diciplina1.setNome("Modelagem 3D");
        diciplina1.setQtsAulas(800);
        diciplina1.setCodigo("MD123");
        diciplina1.setCurso(curso1);
        diciplina1.setCargaHoraria(600);

        Diciplina diciplina2 = new Diciplina();
        diciplina2.setNome("Unit");
        diciplina2.setQtsAulas(400);
        diciplina2.setCodigo("UN123");
        diciplina2.setCurso(curso1);
        diciplina2.setCargaHoraria(200);

        Diciplina diciplina3 = new Diciplina();
        diciplina3.setNome("HTML");
        diciplina3.setQtsAulas(400);
        diciplina3.setCodigo("HT123");
        diciplina3.setCurso(curso2);
        diciplina3.setCargaHoraria(200);

        repository.saveAll(List.of(diciplina1, diciplina2, diciplina3));
    }
}