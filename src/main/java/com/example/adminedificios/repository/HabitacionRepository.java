package com.example.adminedificios.repository;

import com.example.adminedificios.model.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, BigDecimal> {

}
