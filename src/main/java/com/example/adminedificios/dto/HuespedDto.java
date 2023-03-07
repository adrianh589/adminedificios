package com.example.adminedificios.dto;

import com.example.adminedificios.model.Habitacion;
import com.example.adminedificios.model.TipoIdentificacion;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class HuespedDto {
    BigDecimal id;
    String no_identificacion;
    String nombre;
    String apellido;
    String nombreCompleto;
    BigDecimal dias_retraso;
    BigDecimal precio_mora;
    Boolean activo;
    List<Habitacion> habitacion;
    TipoIdentificacion tipoIdentificacion;

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = this.getNombre() + " " + this.getApellido();
    }
}
