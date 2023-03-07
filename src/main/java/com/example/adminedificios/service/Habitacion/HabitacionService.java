package com.example.adminedificios.service.Habitacion;

import com.example.adminedificios.model.Habitacion;

import java.math.BigDecimal;
import java.util.List;

public interface HabitacionService {
    Habitacion create(Habitacion habitacion);
    List<Habitacion> getAll();
    Habitacion getById(BigDecimal id);
    Habitacion update(Habitacion habitacion);
    Habitacion delete(BigDecimal id);
}
