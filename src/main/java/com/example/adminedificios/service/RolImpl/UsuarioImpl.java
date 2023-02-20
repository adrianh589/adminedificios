package com.example.adminedificios.service.RolImpl;

import com.example.adminedificios.dto.UsuariosDto;
import com.example.adminedificios.model.Rol;
import com.example.adminedificios.model.Usuario;
import com.example.adminedificios.repository.UsuarioRepository;
import com.example.adminedificios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UsuarioImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UsuariosDto> findAll() {
       List<UsuariosDto> usuarios = new ArrayList<>();
         usuarioRepository.findAll().forEach(usuario -> {
              UsuariosDto usuarioDto = new UsuariosDto();
              usuarioDto.setId(usuario.getId());
              usuarioDto.setNombre(usuario.getNombre());
              usuarioDto.setPassword(usuario.getPassword());
              usuarioDto.setRoles((List<Rol>) usuario.getRoles());
              usuarios.add(usuarioDto);
         });
         return usuarios;
    }
}
