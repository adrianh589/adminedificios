package com.example.adminedificios.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "habitaciones")
public class Habitacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private BigDecimal id;

    @Column(name = "id_torre")
    private BigDecimal id_torre;

    @Column(name = "id_huesped")
    private BigDecimal id_huesped;

    @Column(name = "no_habitacion")
    private String no_habitacion;

    @Column(name = "costo_habitacion")
    private BigDecimal costo_habitacion;

    @ManyToOne
    @JoinColumn(name = "id_torre", insertable = false, updatable = false)
    private Torre torre;
}
