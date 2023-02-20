package com.example.adminedificios.service.RolImpl;

import com.example.adminedificios.model.Rol;
import com.example.adminedificios.repository.RolRepository;
import com.example.adminedificios.service.RolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class RolImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    @Transactional
    public void saveRol(Rol rol) {
        rolRepository.save(rol);
    }

    @Override
    @Transactional
    public Rol deleteRol(Rol rol) {
        Rol rolJPA = rolRepository.findById(rol.getId()).orElse(null);
        // Si no existe el rol, se debe notificar al usuario que no se puede eliminar el rol porque no existe
        if(rolJPA == null) return null;
        rolRepository.deleteById(rol.getId());
        return rolJPA;
    }

    @Override
    @Transactional
    public void updateRol(Rol rol) { rolRepository.save(rol); }

    @Override
    @Transactional(readOnly = true)
    public Rol getRol(Long id) { return rolRepository.findById(id).orElse(null); }

    @Override
    @Transactional(readOnly = true)
    public List<Rol> getAllRol() { return rolRepository.findAll(); }

    @Override
    @Transactional
    public List<Rol> getRolesByName(String name) { return rolRepository.findByNombreContainingIgnoreCase(name); }
}
