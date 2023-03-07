package com.example.adminedificios.dto;

import com.example.adminedificios.model.TipoIdentificacion;
import lombok.Data;

@Data
public class TipoIdentificacionDto {
    Long id;
    String nombre;
    TipoIdentificacion tipoIdentificacion;
}
