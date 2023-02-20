package com.example.adminedificios.controller;

import com.example.adminedificios.dto.UsuariosDto;
import com.example.adminedificios.model.Usuario;
import com.example.adminedificios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/getAll")
    public List<UsuariosDto> getAll() {
        return usuarioService.findAll();
    }

}
