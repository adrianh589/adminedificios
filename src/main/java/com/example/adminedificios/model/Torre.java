package com.example.adminedificios.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "torres")
public class Torre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    BigDecimal id;

    @Column(name = "torre_no")
    BigDecimal torre_no;
}

