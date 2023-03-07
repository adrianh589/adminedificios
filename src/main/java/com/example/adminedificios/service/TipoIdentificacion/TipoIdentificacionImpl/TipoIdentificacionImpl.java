package com.example.adminedificios.service.TipoIdentificacion.TipoIdentificacionImpl;

import com.example.adminedificios.model.TipoIdentificacion;
import com.example.adminedificios.repository.TipoIdentificacionRepository;
import com.example.adminedificios.service.TipoIdentificacion.TipoIdentificacionService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class TipoIdentificacionImpl implements TipoIdentificacionService {

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    TipoIdentificacionRepository tipoIdentificacionRepository;

    @Override
    @Transactional
    public TipoIdentificacion save(TipoIdentificacion tipoIdentificacion) {
        return tipoIdentificacionRepository.save(tipoIdentificacion);
    }

    @Override
    @Transactional(readOnly = true)
    public TipoIdentificacion get(BigDecimal id) {
        return tipoIdentificacionRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoIdentificacion> getAll() {
        return tipoIdentificacionRepository.findAll();
    }

    @Override
    @Transactional
    public TipoIdentificacion update(TipoIdentificacion tipoIdentificacion) {
        TipoIdentificacion tipoIdentificacionUpdate = get(tipoIdentificacion.getId());
        if (tipoIdentificacionUpdate != null) {
            TipoIdentificacion tipoIdentificacionNoUpdate = new TipoIdentificacion();
            tipoIdentificacionNoUpdate.setId(tipoIdentificacionUpdate.getId());
            tipoIdentificacionNoUpdate.setNombre(tipoIdentificacionUpdate.getNombre());
            tipoIdentificacionRepository.save(tipoIdentificacion); // guarda el objeto actualizado en la base de datos
            return tipoIdentificacionNoUpdate;
        }
        return null;
    }


    @Override
    @Transactional
    public TipoIdentificacion delete(BigDecimal id) {
        TipoIdentificacion tipoIdentificacionDelete = get(id);
        if (tipoIdentificacionDelete != null) {
            tipoIdentificacionRepository.delete(tipoIdentificacionDelete);
        }
        return tipoIdentificacionDelete;
    }
}
