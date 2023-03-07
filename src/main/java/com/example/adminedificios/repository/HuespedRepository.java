package com.example.adminedificios.repository;

import com.example.adminedificios.model.Huesped;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface HuespedRepository extends JpaRepository<Huesped, BigDecimal> {

}
