package com.example.adminedificios.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "huespedes")
public class Huesped implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private BigDecimal id;

    @Column(name = "tipo_identificacion")
    private BigDecimal tipo_identificacion;

    @Column(name = "no_identificacion")
    private String no_identificacion;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "dias_retraso")
    private BigDecimal dias_retraso;

    @Column(name = "precio_mora")
    private BigDecimal precio_mora;

    @Column(name = "activo")
    private Boolean activo;

    @ManyToOne
    @JoinColumn(name = "tipo_identificacion", insertable = false, updatable = false)
    private TipoIdentificacion tipoIdentificacion;

    public void setDias_retraso(BigDecimal dias) {
        this.dias_retraso = (dias == null) ? BigDecimal.valueOf(0) : dias;
    }

    public void setPrecio_mora(BigDecimal precio_mora) {
        this.precio_mora = (precio_mora == null) ? BigDecimal.valueOf(0) : precio_mora;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo == null || activo;
    }

}
