package com.example.adminedificios.controller;

import com.example.adminedificios.dto.TorreDto;
import com.example.adminedificios.model.Torre;
import com.example.adminedificios.service.Torre.TorreService;
import com.example.adminedificios.utils.Map;
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
@RequestMapping("/torres")
public class TorreController {

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private TorreService torreService;
    java.util.Map<String, Object> map = new HashMap<String, Object>();

    @GetMapping("/getAll")
    public ResponseEntity<List<TorreDto>> findAll() {
        map = new HashMap<String, Object>();
        List<TorreDto> torreDto = Map.mapAll(torreService.findAll(), TorreDto.class, modelMapper);
        return ResponseEntity.ok(torreDto);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<HashMap<String, Object>> findById(@PathVariable BigDecimal id) {
        try{
            map = new HashMap<String, Object>();
            map.put("ok", true);
            map.put("message", "Torre encontrada");
            map.put("response", modelMapper.map(torreService.findById(id), TorreDto.class));
        }catch (Exception e) {
            map.put("ok", false);
            map.put("message", "No se encontro ninguna torre por ese id o hubo un error interno");
        }
        return ResponseEntity.ok((HashMap<String, Object>) map);
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody TorreDto torreDto) {
        try {
            map = new HashMap<String, Object>();
            map.put("response", torreService.save(modelMapper.map(torreDto, Torre.class)));
            map.put("ok", true);
            map.put("message", "Torre guardada correctamente");
        }catch (Exception e){
            map.put("ok", false);
            map.put("message", e.getMessage());
        }
        return ResponseEntity.ok(map);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable BigDecimal id) {
        try {
            map = new HashMap<String, Object>();
            log.info("Id para borrar" ,id);
            TorreDto torreDto = modelMapper.map(torreService.deleteById(id), TorreDto.class);
            if (torreDto != null) {
                map.put("response", torreDto);
                map.put("ok", true);
                map.put("message", "Torre eliminada correctamente");
            } else {
                map.put("ok", false);
                map.put("message", "La torre no ha sido encontrada");
            }
        }catch (Exception e) {
            map = new HashMap<String, Object>();
            map.put("ok", false);
            map.put("message", "Error al borrar la torre");
        }
        return ResponseEntity.ok(map);
    }

}
