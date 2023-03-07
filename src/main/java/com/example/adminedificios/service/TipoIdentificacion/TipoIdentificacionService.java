package com.example.adminedificios.service.TipoIdentificacion;

import com.example.adminedificios.model.TipoIdentificacion;

import java.math.BigDecimal;
import java.util.List;

public interface TipoIdentificacionService {
    TipoIdentificacion save(TipoIdentificacion tipoIdentificacion);
    TipoIdentificacion get(BigDecimal id);
    List<TipoIdentificacion> getAll();
    TipoIdentificacion update(TipoIdentificacion tipoIdentificacion);
    TipoIdentificacion delete(BigDecimal id);
}
