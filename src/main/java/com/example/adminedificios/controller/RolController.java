package com.example.adminedificios.controller;

import com.example.adminedificios.dto.RolDto;
import com.example.adminedificios.model.Rol;
import com.example.adminedificios.service.Rol.RolService;
import com.example.adminedificios.utils.Map;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/roles")
public class RolController {

    private final ModelMapper modelMapper = new ModelMapper();

    java.util.Map<String, Object> map = new HashMap<String, Object>();

    @Autowired
    private RolService rolService;

    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        /*List<Rol> roles = rolService.getAllRol();
        List<RolDto> rolesDto = new ArrayList<>();

        for (Rol rol : roles) {
            RolDto rolDto = new RolDto();
            rolDto.setId(rol.getId());                // Forma tradicional de mapear los objetos
            rolDto.setNombre(rol.getNombre());
            rolDto.setUsuarios(null);
            rolesDto.add(rolDto);
        }
        return rolesDto;*/


        /*List<Rol> roles = rolService.getAllRol();
        List<RolDto> rolesDto = new ArrayList<>();
        for (Rol rol : roles) {
            // Mapear los objetos omitiendo el atributo usuarios
            RolDto rolDto = modelMapper.map(rol, RolDto.class); // Forma con model mapper (Tradicional)
            rolDto.setUsuarios(null);
            rolesDto.add(rolDto);
        }
        return ResponseEntity.ok(rolesDto);*/
        try {
            var roles = rolService.getAllRol();
            var rolesDto = Map.mapAll(roles, RolDto.class, modelMapper); // Mapear los objetos omitiendo el atributo usuarios usando la funcion mapAll omitiendo el atributo usuarios (Forma recortada)
            return ResponseEntity.ok(rolesDto);
        } catch (Exception e) {
            log.error("Error al obtener los roles", e);
            map.put("ok", false);
            map.put("message", "Error al obtener los roles");
            map.put("error", e.getMessage());
            return new ResponseEntity(map, HttpStatus.valueOf(500));
        }

    }

    @GetMapping("/get/{id}")
    public ResponseEntity getRol(@PathVariable("id") Long id) {
        try {
            Rol rol = rolService.getRol(id);
            if (rol == null) {
                map.put("ok", false);
                map.put("message", "No se encontro el rol");
                map.put("status", "Not Found");
                map.put("error", "404");
                return new ResponseEntity(map, HttpStatus.valueOf(404));
            }
            RolDto rolDto = modelMapper.map(rol, RolDto.class);
            rolDto.setUsuarios(null);
            map.put("ok", true);
            map.put("message", "El rol se encontro con exito");
            map.put("error", null);
            map.put("response", rolDto);
            return ResponseEntity.ok(map);
        } catch (Exception e) {
            log.error("Error al obtener el rol", e);
            map.put("ok", false);
            map.put("message", "Error al obtener el rol");
            map.put("error", e.getMessage());
            return new ResponseEntity(map, HttpStatus.valueOf(500));
        }
    }

    @PostMapping("/getByName")
    public ResponseEntity<List<RolDto>> getRolByName(@RequestBody RolDto rol) {
        try {
            var res = rolService.getRolesByName(rol.getNombre());
            if (res.isEmpty()) {
                map.put("ok", false);
                map.put("message", "No hay roles con el nombre proporcionado: " + rol.getNombre());
                map.put("status", "Not Found");
                map.put("error", "404");
                return new ResponseEntity(map, HttpStatus.valueOf(404));
            }
            return ResponseEntity.ok(Map.mapAll(rolService.getRolesByName(rol.getNombre()), RolDto.class, modelMapper));
        } catch (Exception e) {
            log.error("Error al obtener el rol", e);
            map.put("ok", false);
            map.put("message", "Error al obtener el rol");
            map.put("error", e.getMessage());
            return new ResponseEntity(map, HttpStatus.valueOf(500));
        }
    }

    @PostMapping("/save")
    public ResponseEntity saveRol(@RequestBody RolDto rol) {
        try {
            map = new HashMap<String, Object>();
            Rol rolEntity = modelMapper.map(rol, Rol.class);
            var res = rolService.saveRol(rolEntity);
            if (res == null) {
                map.put("ok", false);
                map.put("message", "No se pudo guardar el rol");
            }
            map.put("ok", true);
            map.put("message", "Rol guardado correctamente");
            map.put("rol", res);
        } catch (Exception e) {
            map.put("ok", false);
            map.put("message", "No se pudo guardar el rol debido a un error interno");
            map.put("message_details", e.getMessage());
        }
        return ResponseEntity.ok(map);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteRol(@RequestBody RolDto rol, HttpStatus status) {
        map = new HashMap<String, Object>();
        Rol rolEntity = modelMapper.map(rol, Rol.class);
        var res = rolService.deleteRol(rolEntity);
        if (res == null) {
            map.put("message", "No existe el rol que desea eliminar");
            return new ResponseEntity<Object>(map, HttpStatus.valueOf(505));
        }
        map.put("ok", true);
        map.put("message", "Rol eliminado correctamente");
        map.put("rol", res);
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity updateRol(@RequestBody RolDto rol) {
        try {
            map = new HashMap<String, Object>();
            var res = rolService.getRol(rol.getId());
            if (res == null) {
                map.put("ok", false);
                map.put("message", "No se encontro el rol");
                return new ResponseEntity(map, HttpStatus.valueOf(404));
            }
            Rol rolEntity = modelMapper.map(rol, Rol.class);
            res = rolService.updateRol(rolEntity);
            map.put("ok", true);
            map.put("message", "Rol actualizado correctamente");
            map.put("rol", res);
        } catch (Exception e) {
            map.put("ok", false);
            map.put("message", "No se pudo actualizar el rol debido a un error interno");
            map.put("message_details", e.getMessage());
            return new ResponseEntity(map, HttpStatus.valueOf(505));
        }
        return ResponseEntity.ok(map);
    }

}
