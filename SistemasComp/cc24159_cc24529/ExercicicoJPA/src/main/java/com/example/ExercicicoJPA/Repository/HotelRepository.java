package com.example.ExercicicoJPA.Repository;

import com.example.ExercicicoJPA.Model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
}
