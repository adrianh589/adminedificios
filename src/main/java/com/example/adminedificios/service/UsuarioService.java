package com.example.adminedificios.service;

import com.example.adminedificios.dto.UsuariosDto;
import com.example.adminedificios.model.Usuario;

import java.util.List;

public interface UsuarioService {
    List<UsuariosDto> findAll();
}
