package com.example.ExercicicoJPA.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Data
@Table
public class Hospede {
    @Id
    @Column
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String cpf;

    @Column
    private String nomeCompleto;

    @Column
    private String dataNascimento;

    @Column
    private String email;

    @Column
    private String celular;

    @OneToMany
    private List<Reserva> reservas;
}
