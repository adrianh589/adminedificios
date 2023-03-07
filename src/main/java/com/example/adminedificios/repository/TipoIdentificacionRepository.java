package com.example.adminedificios.repository;

import com.example.adminedificios.model.TipoIdentificacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface TipoIdentificacionRepository extends JpaRepository<TipoIdentificacion, BigDecimal> {

}
