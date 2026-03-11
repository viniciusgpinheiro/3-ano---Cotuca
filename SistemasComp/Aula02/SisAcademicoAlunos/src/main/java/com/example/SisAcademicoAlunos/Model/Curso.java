package com.example.SisAcademicoAlunos.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    @Transient
    private List<Diciplina> diciplinas;
}
