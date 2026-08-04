package com.example.SisAcademicoAlunos.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.beans.ConstructorProperties;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "avaliacao")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(length = 80, nullable = false)
    private String nome;

    @Column
    private LocalDate data;

    @Column
    private Double nota;

    // 1 avaliacao tem 1 professor
    @ManyToOne
    @JoinColumn(name = "idProfessor", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Professor professor;

    // 1 avaliacao tem 1 diciplina
    @ManyToOne
    @JoinColumn(name = "idDiciplina", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Diciplina diciplinas;

    // 1 avaliacao tem 1 aluno
    @ManyToOne
    @JoinColumn(name = "idAluno", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Aluno aluno;

}
