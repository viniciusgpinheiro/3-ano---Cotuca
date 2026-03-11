package com.example.SisAcademicoAlunos.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    private Curso curso;
}
