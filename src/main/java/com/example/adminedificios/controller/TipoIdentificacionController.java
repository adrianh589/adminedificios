package com.example.adminedificios.controller;

import com.example.adminedificios.dto.TipoIdentificacionDto;
import com.example.adminedificios.model.TipoIdentificacion;
import com.example.adminedificios.service.TipoIdentificacion.TipoIdentificacionService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/tiposIdentificacion")
@Slf4j
public class TipoIdentificacionController {

    @Autowired
    TipoIdentificacionService tipoIdentificacionService;

    ModelMapper modelMapper = new ModelMapper();

    HashMap map = new HashMap<>();

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody TipoIdentificacionDto tipoIdentificacionDto) {
        try {
            log.info(tipoIdentificacionDto.toString());
            log.info(modelMapper.map(tipoIdentificacionDto, TipoIdentificacion.class).toString());
            TipoIdentificacion tipoIdentificacionSave = tipoIdentificacionService.save(modelMapper.map(tipoIdentificacionDto, TipoIdentificacion.class));
            map.put("ok", true);
            map.put("message", "Tipo de identificacion guardado");
            map.put("response", tipoIdentificacionSave);
        } catch (Exception e) {
            map.put("ok", false);
            map.put("message", "Fallo al guardar el tipo de identificacion" + e.getMessage());
            map.put("response", null);
        }
        return ResponseEntity.ok(map);
    }

    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        try {
            List<TipoIdentificacion> tipoIdentificacionList = tipoIdentificacionService.getAll();
            if (tipoIdentificacionList.isEmpty()) {
                map.put("ok", false);
                map.put("message", "No se encontraron tipos de identificacion");
                map.put("response", null);
            } else {
                map.put("message", "OK");
                map.put("ok", true);
                map.put("message", "Tipos de identificacion encontrados");
                map.put("response", tipoIdentificacionService.getAll());
            }
        } catch (Exception e) {
            map.put("ok", false);
            map.put("message", "Fallo al consultar los tipos de identificacion" + e.getMessage());
            map.put("response", null);
        }
        return ResponseEntity.ok(map);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable BigDecimal id) {
        try {
            TipoIdentificacion tipoIdentificacion = tipoIdentificacionService.get(id);
            if (tipoIdentificacion == null) {
                map.put("ok", false);
                map.put("message", "No se encontró el tipo de identificacion con id: " + id);
                map.put("response", null);
            } else {
                map.put("ok", true);
                map.put("message", "Tipo de identificacion encontrado");
                map.put("response", tipoIdentificacion);
            }
        } catch (Exception e) {
            map.put("ok", false);
            map.put("message", "Fallo al consultar el tipo de identificacion" + e.getMessage());
            map.put("response", null);
        }
        return ResponseEntity.ok(map);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody TipoIdentificacionDto tipoIdentificacionDto) {
        try {
            TipoIdentificacion tipoIdentificacionUpdate = tipoIdentificacionService.update(modelMapper.map(tipoIdentificacionDto, TipoIdentificacion.class));
            if (tipoIdentificacionUpdate == null) {
                map.put("ok", false);
                map.put("message", "No se encontró el tipo de identificacion con id: " + tipoIdentificacionDto.getId());
                map.put("response", null);
            } else {
                map.put("ok", true);
                map.put("message", "Tipo de identificacion actualizado");

                HashMap mapUpdated = new HashMap<>();
                mapUpdated.put("anterior", tipoIdentificacionUpdate);
                mapUpdated.put("actual", tipoIdentificacionDto);

                map.put("response",  mapUpdated);
            }
        } catch (Exception e) {
            map.put("ok", false);
            map.put("message", "Fallo al actualizar el tipo de identificacion" + e.getMessage());
            map.put("response", null);
        }
        return ResponseEntity.ok(map);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable BigDecimal id) {
        try {
            TipoIdentificacion tipoIdentificacionDelete = tipoIdentificacionService.delete(id);
            if (tipoIdentificacionDelete == null) {
                map.put("ok", false);
                map.put("message", "No se encontró el tipo de identificacion con id: " + id);
                map.put("response", null);
            } else {
                map.put("ok", true);
                map.put("message", "Tipo de identificacion eliminado");
                map.put("response", tipoIdentificacionDelete);
            }
        } catch (Exception e) {
            map.put("ok", false);
            map.put("message", "Fallo al eliminar el tipo de identificacion" + e.getMessage());
            map.put("response", null);
        }
        return ResponseEntity.ok(map);
    }

}
