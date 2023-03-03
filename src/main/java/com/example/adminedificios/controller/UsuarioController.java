package com.example.adminedificios.controller;

import com.example.adminedificios.dto.UsuariosDto;
import com.example.adminedificios.model.Usuario;
import com.example.adminedificios.service.Usuario.UsuarioService;
import com.example.adminedificios.utils.Map;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/usuarios")
@Slf4j
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    private ModelMapper modelMapper = new ModelMapper();
    java.util.Map<String, Object> map = new HashMap<String, Object>();

    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        try {
            map = new HashMap<String, Object>();
            map.put("ok", true);
            map.put("mensaje", "Usuarios obtenidos correctamente");
            map.put("response", Map.mapAll(usuarioService.getAll(), UsuariosDto.class, modelMapper));
            return ResponseEntity.ok(map);
        } catch (Exception e) {
            map.put("ok", false);
            map.put("mensaje", "Error al obtener los usuarios");
            return ResponseEntity.status(500).body(map);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        try {
            map = new HashMap<String, Object>();
            map.put("ok", true);
            map.put("mensaje", "Usuario obtenido correctamente");
            map.put("response", modelMapper.map(usuarioService.getUsuarioById(id), UsuariosDto.class));
            return ResponseEntity.ok(map);
        } catch (Exception e) {
            map.put("ok", false);
            map.put("mensaje", "Error al obtener el usuario");
            return ResponseEntity.status(500).body(map);
        }
    }

    @GetMapping("/getByName")
    public ResponseEntity getByName(@RequestBody Usuario usuario) {
        try {
            map = new HashMap<String, Object>();
            map.put("ok", true);
            map.put("mensaje", "Usuario obtenido correctamente");
            map.put("response", modelMapper.map(usuarioService.getUsuarioByNombre(usuario.getNombre()), UsuariosDto.class));
            return ResponseEntity.ok(map);
        } catch (Exception e) {
            map.put("ok", false);
            map.put("mensaje", "Error al obtener el usuario con el nombre: " + usuario.getNombre());
            return ResponseEntity.status(500).body(map);
        }
    }

    @GetMapping("/save")
    public ResponseEntity save(@RequestBody Usuario usuario) {
        try {
            map = new HashMap<String, Object>();
            map.put("ok", true);
            map.put("mensaje", "Usuario guardado correctamente");
            map.put("response", modelMapper.map(usuarioService.saveUsuario(usuario), UsuariosDto.class));
            return ResponseEntity.ok(map);
        } catch (Exception e) {
            map.put("ok", false);
            map.put("mensaje", "Error al guardar el usuario");
            return ResponseEntity.status(500).body(map);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        try {
            map = new HashMap<String, Object>();
            var usuario = usuarioService.getUsuarioById(id);
            log.info("Usuario: " + usuario);
            if (usuario == null) {
                map.put("ok", false);
                map.put("mensaje", "El usuario no existe");
                return ResponseEntity.status(500).body(map);
            }
            map.put("ok", true);
            map.put("mensaje", "Usuario eliminado correctamente");
            map.put("response", modelMapper.map(usuarioService.deleteUsuario(id), UsuariosDto.class));
            return ResponseEntity.ok(map);
        } catch (Exception e) {
            map.put("ok", false);
            map.put("mensaje", "Error interno al eliminar el usuario ");
            return ResponseEntity.status(500).body(map);
        }
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Usuario usuario) {
        try {
            map = new HashMap<String, Object>();
            var usuario1 = usuarioService.getUsuarioById(usuario.getId());
            if (usuario1 == null) {
                map.put("ok", false);
                map.put("mensaje", "El usuario no existe");
                return ResponseEntity.status(500).body(map);
            }
            map.put("ok", true);
            map.put("mensaje", "Usuario actualizado correctamente");
            map.put("response", modelMapper.map(usuarioService.updateUsuario(usuario), UsuariosDto.class));
            return ResponseEntity.ok(map);
        } catch (Exception e) {
            map.put("ok", false);
            map.put("mensaje", "Error interno al actualizar el usuario ");
            return ResponseEntity.status(500).body(map);
        }
    }


}
