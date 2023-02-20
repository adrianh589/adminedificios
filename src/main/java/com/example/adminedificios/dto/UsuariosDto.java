package com.example.adminedificios.dto;

import com.example.adminedificios.model.Rol;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsuariosDto {
    private Long id;
    private String nombre;
    private String password;
    private List<Rol> roles;
}
