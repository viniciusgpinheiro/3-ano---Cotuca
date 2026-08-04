package com.example.ExercicicoJPA.Repository;

import com.example.ExercicicoJPA.Model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
}
