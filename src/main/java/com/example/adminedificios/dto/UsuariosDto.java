package com.example.adminedificios.dto;

import com.example.adminedificios.model.Rol;
import lombok.Data;

import java.util.List;

@Data
public class UsuariosDto {
    private Long id;
    private String nombre;
    private String password;
    private List<Rol> roles;
}
