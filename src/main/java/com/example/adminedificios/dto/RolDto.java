package com.example.adminedificios.dto;

import com.example.adminedificios.model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class RolDto {
    private Long id;
    private String nombre;
    @JsonIgnore
    private Set<Usuario> usuarios = new HashSet<>();
}
