package com.example.adminedificios.service.Torre;

import com.example.adminedificios.model.Torre;

import java.math.BigDecimal;
import java.util.List;

public interface TorreService {
    public List<Torre> findAll();
    public Torre findById(BigDecimal id);
    public Torre save(Torre torre);
    public Torre deleteById(BigDecimal id);
}
