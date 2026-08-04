package com.example.ExercicicoJPA.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table
public class Hotel {
    @Id
    @Column
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nome;

    @Column
    private String endereco;

    @Column
    private String telefone;

    @Column
    private String site;

    @OneToMany
    private List<Quarto> quartos;
}
