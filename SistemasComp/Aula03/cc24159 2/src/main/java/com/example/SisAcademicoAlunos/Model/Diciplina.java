package com.example.SisAcademicoAlunos.Model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name="diciplina")
public class Diciplina {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo", length = 5, nullable = false)
    private String codigo;

    @Column(name = "nome", length = 80, nullable = false)
    private String nome;

    @Column(name="CargaHoraria")
    private Integer cargaHoraria;

    @Column(name = "qtsAulas", nullable = false)
    private Integer qtsAulas;

    // N diciplinas para 1 curso
    @ManyToOne
    @JoinColumn(name = "idCurso", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Curso curso;

    // N diciplinas para N professor
    @ManyToMany(mappedBy = "diciplinas")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Professor> professores;

    // 1 diciplina tem N avaliacoes
    @OneToMany(mappedBy = "diciplinas")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Avaliacao> avaliacoes;
}
