package com.example.SisAcademicoAlunos.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Table(name="curso")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "codigo", length = 5, nullable = false)
    private String codigo;

    @Column(name = "nome", length = 80, nullable = false)
    private String nome;

    @Column(name="CargaHoraria")
    private Integer cargaHoraria;

    // 1 Curso para N diciplinas
    @OneToMany(mappedBy = "curso")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Diciplina> diciplinas;

    // N cursos para 1 departamento
    @ManyToOne
    @JoinColumn(name = "idDepartamento", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Departamento departamento;

    // N cursos para 1 matricula
    @OneToMany(mappedBy = "curso")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Matricula> matriculas;
}
