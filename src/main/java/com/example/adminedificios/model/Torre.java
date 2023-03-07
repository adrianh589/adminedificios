package com.example.adminedificios.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Data
@Table(name = "torres")
public class Torre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    BigDecimal id;

    @Column(name = "torre_no")
    BigDecimal torre_no;

}

