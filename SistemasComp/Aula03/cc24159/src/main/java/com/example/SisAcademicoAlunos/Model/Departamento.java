package com.example.SisAcademicoAlunos.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@Table(name="departamento")
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", length = 80, nullable = false)
    private String nome;

    // 1 Curso para N diciplinas
    @OneToMany(mappedBy = "departamento")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Curso> cursos;

    // 1 professor para N departamentos
    @OneToMany(mappedBy = "departamento")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Professor> professores;
}
