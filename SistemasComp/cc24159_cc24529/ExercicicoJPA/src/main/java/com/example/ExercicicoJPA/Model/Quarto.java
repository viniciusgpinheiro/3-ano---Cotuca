package com.example.ExercicicoJPA.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table
public class Quarto {
    @Id
    @Column
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer numero;

    @Column
    private Integer capacidade;

    @Column
    private String status;

    @Column
    private double precoDiaria;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name="idTipoQuarto")
    private TipoQuarto tipoQuarto;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name="idHotel")
    private Hotel hotel;

    @OneToMany
    private List<Reserva> reservas;
}
