package com.example.adminedificios.service;

import com.example.adminedificios.model.Rol;

import java.util.List;

public interface RolService {
    void saveRol(Rol rol);
    Rol deleteRol(Rol rol);
    void updateRol(Rol rol);
    Rol getRol(Long id);
    List<Rol> getAllRol();
    List<Rol> getRolesByName(String name);
}
