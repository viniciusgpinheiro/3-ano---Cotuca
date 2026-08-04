package com.example.SisAcademicoAlunos.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name="professor")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(length = 5, nullable = false)
    private Integer matricula;

    @Column(length = 80, nullable = false)
    private String nome;

    @Column(length = 80, nullable = false)
    private String email;

    @Column(length = 11, nullable = false)
    private Integer celular;

    @Column
    private LocalDate dataNascimento;

    // N professor para N diciplinas
    @ManyToMany
    @JoinTable(
            name = "professorDiciplina",
            joinColumns = @JoinColumn(name = "idProfessor"),
            inverseJoinColumns = @JoinColumn(name = "idDiciplina")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Diciplina> diciplinas;

    // 1 professor para 1 departamento
    @ManyToOne
    @JoinColumn(name = "idDepartamento", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Departamento departamento;

    // 1 professor tem N avaliacoes
    @OneToMany(mappedBy = "professor")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Avaliacao> avaliacoes;
}
