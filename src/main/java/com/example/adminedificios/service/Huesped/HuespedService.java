package com.example.adminedificios.service.Huesped;

import com.example.adminedificios.model.Huesped;

import java.math.BigDecimal;
import java.util.List;

public interface HuespedService {
    Huesped save(Huesped huesped);
    List<Huesped> getAll();
    Huesped get(BigDecimal id);
    Huesped update(Huesped huesped);
    Huesped delete(BigDecimal id);

}
