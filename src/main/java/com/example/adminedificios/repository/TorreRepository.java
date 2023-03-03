package com.example.adminedificios.repository;

import com.example.adminedificios.model.Torre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface TorreRepository extends JpaRepository<Torre, BigDecimal> {

}
