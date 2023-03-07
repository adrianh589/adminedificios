package com.example.adminedificios.controller;

import com.example.adminedificios.dto.HuespedDto;
import com.example.adminedificios.model.Huesped;
import com.example.adminedificios.service.Huesped.HuespedService;
import com.example.adminedificios.utils.Map;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/huespedes")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HuespedController {

    @Autowired
    HuespedService huespedService;

    ModelMapper modelMapper = new ModelMapper();
    HashMap<String, Object> map = new HashMap<>();

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody HuespedDto huespedDto){
        try {
            log.info(huespedDto.toString());
            map.put("ok", true);
            map.put("message", "Huesped guardado correctamente");
            map.put("response", huespedService.save(modelMapper.map(huespedDto, Huesped.class)));
        } catch ( Exception e ) {
            map.put("ok", false);
            map.put("message", "Error al guardar el huesped debido a un error interno");
            map.put("response", null);
        }
        return ResponseEntity.ok(map);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable BigDecimal id){
        try {
            Huesped huesped = huespedService.get(id);
            if (huesped != null) {
                map.put("ok", true);
                map.put("message", "Huesped obtenido correctamente");
                map.put("response", modelMapper.map(huesped, HuespedDto.class));
            } else {
                map.put("ok", false);
                map.put("message", "No hay huespedes que tengan el id: " + id);
                map.put("response", null);
            }
        } catch (Exception e) {
            map.put("ok", false);
            map.put("message", "Fallo al obtener huesped debido a un error interno");
            map.put("response", null);
        }
        return ResponseEntity.ok(map);
    }

    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        try {
            List<Huesped> huespedes = huespedService.getAll();
            if (huespedes != null) {
                map.put("ok", true);
                map.put("message", "Huespedes obtenidos correctamente");
                map.put("response", Map.mapAll(huespedes, HuespedDto.class, modelMapper));
            } else {
                map.put("ok", false);
                map.put("message", "No hay huespedes en la base de datos");
                map.put("response", huespedes);
            }
        } catch (Exception e) {
            map.put("ok", false);
            map.put("message", "Fallo al obtener huespedes debido a un error interno");
            map.put("response", null);
        }
        return ResponseEntity.ok(map);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody HuespedDto huespedDto) {
        try {
            Huesped huesped = huespedService.update(modelMapper.map(huespedDto, Huesped.class));
            if (huesped != null) {
                map.put("ok", true);
                map.put("message", "Huesped actualizado correctamente");
                map.put("response", modelMapper.map(huesped, HuespedDto.class));
            } else {
                map.put("ok", false);
                map.put("message", "No se pudo actualizar el huesped porque no existe en la base de datos con el id: " + huespedDto.getId());
                map.put("response", null);
            }
        } catch (Exception e) {
            map.put("ok", false);
            map.put("message", "Fallo al actualizar huesped debido a un error interno: " + e.getMessage());
            map.put("response", null);
        }
        return ResponseEntity.ok(map);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable BigDecimal id) {
        try {
            Huesped huesped = huespedService.delete(id);
            if (huesped != null) {
                map.put("ok", true);
                map.put("message", "Huesped eliminado correctamente");
                map.put("response", modelMapper.map(huesped, HuespedDto.class));
            } else {
                map.put("ok", false);
                map.put("message", "No se pudo eliminar el huesped porque no existe en la base de datos con el id: " + id);
                map.put("response", null);
            }
        } catch (Exception e) {
            map.put("ok", false);
            map.put("message", "Fallo al eliminar huesped debido a un error interno: " + e.getMessage());
            map.put("response", null);
        }
        return ResponseEntity.ok(map);
    }
}
