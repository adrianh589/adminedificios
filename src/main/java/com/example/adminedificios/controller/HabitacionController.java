package com.example.adminedificios.controller;

import com.example.adminedificios.dto.HabitacionDto;
import com.example.adminedificios.model.Habitacion;
import com.example.adminedificios.repository.HabitacionRepository;
import com.example.adminedificios.service.Habitacion.HabitacionImpl.HabitacionImpl;
import com.example.adminedificios.service.Habitacion.HabitacionService;
import com.example.adminedificios.utils.Map;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/habitaciones")
@Slf4j
public class HabitacionController {
    @Autowired
    private HabitacionRepository habitacionRepository;

    ModelMapper mapper = new ModelMapper();
    java.util.Map<String, Object> map = new HashMap<String, Object>();

    @Autowired
    HabitacionService habitacionService;

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody HabitacionDto habitacionDto) {
        log.info(habitacionDto.toString());
        try {
            map.put("ok", true);
            map.put("message", "Habitacion guardada correctamente");
            map.put("response", habitacionService.create(mapper.map(habitacionDto, Habitacion.class)));
        } catch (Exception e) {
            map.put("ok", false);
            map.put("message", "Fallo al crear la habitación: " + e.getMessage());
            map.put("response", null);
        }
        return ResponseEntity.ok(map);
    }

    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        try {
            map = new HashMap<>();
            List<Habitacion> habitaciones = habitacionService.getAll();

//            List<HabitacionDto> habitacionesDto = Map.mapAll(habitaciones, HabitacionDto.class, mapper);

//            List<HabitacionDto> habitacionesDto = new ArrayList<>();
//
//            for (Habitacion habitacion : habitaciones) {
//                HabitacionDto habitacionDto = new HabitacionDto();
//                habitacionDto.setId(habitacion.getId());
//                habitacionDto.setNo_habitacion(habitacion.getNo_habitacion());
//                habitacionDto.setCosto_habitacion(habitacion.getCosto_habitacion()); // Forma tradicional (sin model mapper)
//                habitacionDto.setId_huesped(habitacion.getId_huesped());
//                habitacionDto.setId_torre(habitacion.getTorre().getId());
//                habitacionDto.setTorre(habitacion.getTorre());
//                habitacionesDto.add(habitacionDto);
//            }

            List<HabitacionDto> habitacionesDto = Map.mapAll(habitaciones, HabitacionDto.class, mapper);

            map.put("ok", true);
            map.put("message", "Listado de todas las habitaciones");
            map.put("response", habitacionesDto);
            habitacionService.getAll();
        } catch (Exception e) {
            map.put("ok", true);
            map.put("message", "Fallo al consultar las habitaciones");
            map.put("response", null);
        }
        return ResponseEntity.ok(map);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable BigDecimal id) {
        try {
            map = new HashMap<>();
            Habitacion habitacion = habitacionService.getById(id);
            HabitacionDto habitacionDto = mapper.map(habitacion, HabitacionDto.class);
            map.put("ok", true);
            map.put("message", "Habitacion encontrada");
            map.put("response", habitacionDto);
        } catch (Exception e) {
            map.put("ok", true);
            map.put("message", "Fallo al consultar la habitacion");
            map.put("response", null);
        }
        return ResponseEntity.ok(map);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody HabitacionDto habitacionDto){
        try{
            map = new HashMap<>();
            map.put("ok", true);
            map.put("message", "Habitacion guardada correctamente");
            map.put("response", habitacionService.update(mapper.map(habitacionDto, Habitacion.class)));
        }catch (Exception e) {
            map.put("ok", false);
            map.put("message", "Fallo al guardar la habitación");
            map.put("response", null);
        }
        return ResponseEntity.ok(map);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable BigDecimal id){
        try {

            HabitacionDto habitacionDelete = mapper.map(habitacionService.delete(id), HabitacionDto.class);

            if (habitacionDelete != null) {
                map = new HashMap<>();
                map.put("ok", true);
                map.put("message", "Habitacion eliminada correctamente");
                map.put("response", habitacionDelete);
            } else {
                map = new HashMap<>();
                map.put("ok", false);
                map.put("message", "La habitación no ha sido eliminada, no existe una habitación con el id: " + id);
                map.put("response", null);
            }
        } catch (Exception e) {
            map.put("ok", false);
            map.put("message", "Fallo al borrar la habitación");
            map.put("response", null);
        }
        return ResponseEntity.ok(map);
    }

}
