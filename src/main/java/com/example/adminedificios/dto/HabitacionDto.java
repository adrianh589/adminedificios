package com.example.adminedificios.dto;

import com.example.adminedificios.model.Torre;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class HabitacionDto {
    BigDecimal id;
    BigDecimal id_torre;
    BigDecimal id_huesped;
    String no_habitacion;
    BigDecimal costo_habitacion;
    Torre torre;
//    Huesped huesped;
}
