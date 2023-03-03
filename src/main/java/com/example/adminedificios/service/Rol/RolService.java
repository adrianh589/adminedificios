package com.example.adminedificios.service.Rol;

import com.example.adminedificios.model.Rol;

import java.util.List;

public interface RolService {
    Rol saveRol(Rol rol);
    Rol deleteRol(Rol rol);
    Rol updateRol(Rol rol);
    Rol getRol(Long id);
    List<Rol> getAllRol();
    List<Rol> getRolesByName(String name);
}
