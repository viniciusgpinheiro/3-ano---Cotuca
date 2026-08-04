package com.example.SisAcademicoAlunos.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@Table(name="matricula")
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "codigo", length = 5, nullable = false)
    private String codigo;

    @Column(name = "data", length = 80, nullable = false)
    private LocalDate data;

    @Column(name="hora")
    private LocalTime hora;

    // 1 Matricula para N cursos
    @ManyToOne
    @JoinColumn(name = "idCurso", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Curso curso;

    // 1 matricula para 1 aluno
    @OneToOne(mappedBy = "matricula", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Aluno aluno;
}
