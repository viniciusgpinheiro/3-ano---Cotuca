package com.example.ExercicicoJPA.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table
public class TipoQuarto {
    @Id
    @Column
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String descricao;

    @OneToMany
    private List<Quarto> quartos;
}
