package com.example.adminedificios.service.Usuario;

import com.example.adminedificios.model.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> getAll();
    Usuario getUsuarioById(Long id);
    Usuario getUsuarioByNombre(String nombre);
    Usuario saveUsuario(Usuario usuario);
    Usuario updateUsuario(Usuario usuario);
    Usuario deleteUsuario(Long id);
}
