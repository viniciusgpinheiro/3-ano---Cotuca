package com.example.ExercicicoJPA.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.util.List;

@Entity
@Data
@Table
public class Reserva {
    @Id
    @Column
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String dataCheckIn;

    @Column
    private String dataCheckOut;

    @Column
    private Integer qtdDias;

    @Column
    private Double valorTotal;

    @Column
    private String status;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name="idQuarto")
    private Quarto quarto;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name="idHospede")
    private  Hospede hospede;
}


