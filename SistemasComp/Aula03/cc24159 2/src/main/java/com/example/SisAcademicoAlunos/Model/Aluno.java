package com.example.SisAcademicoAlunos.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@Table(name="aluno")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ra", length = 5, nullable = false)
    private String ra;

    @Column(name = "nome", length = 80, nullable = false)
    private String nome;

    @Column(name="email", length = 80, nullable = false)
    private String email;

    // 1 aluno tem 1 matricula
    @OneToOne
    @JoinColumn(name = "idMatricula", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Matricula matricula;

    // 1 aluno tem N avaliacoes
    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Avaliacao> avaliacoes;
}
