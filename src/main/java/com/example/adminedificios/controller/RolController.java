package com.example.adminedificios.controller;

import com.example.adminedificios.model.Rol;
import com.example.adminedificios.service.RolService;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/roles")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping("/getAll")
    public List<Rol> inicio(){
        log.info("Ejecutando el controlador spring mvc");
        return rolService.getAllRol();
    }

    @GetMapping("/get/{id}")
    public Rol getRol(@PathVariable("id") Long id){
        return rolService.getRol(id);
    }

    @PostMapping("/getByName")
    public List<Rol> getRolByName(@RequestBody Rol rol){
        log.info(rol.toString());
        return rolService.getRolesByName(rol.getNombre());
    }

    @PostMapping("/save")
    public Rol saveRol(@RequestBody Rol rol){
        rolService.saveRol(rol);
        return rol;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteRol(@RequestBody Rol rol, HttpStatus status){

        Map<String, Object> map = new HashMap<String, Object>();

        var res = rolService.deleteRol(rol);
        if(res != null) {
            map.put("message", "Rol eliminado correctamente");
            map.put("rol", res);
            return new ResponseEntity<Object>(map, HttpStatus.OK);
        }
        map.put("message", "No se pudo eliminar el rol");
        return new ResponseEntity<Object>(map, HttpStatus.valueOf(505));
    }

    @PutMapping("/update")
    public Rol updateRol(@RequestBody Rol rol){
        rolService.updateRol(rol);
        return rol;
    }

}
