package com.example.adminedificios.service.Habitacion.HabitacionImpl;

import com.example.adminedificios.model.Habitacion;
import com.example.adminedificios.repository.HabitacionRepository;
import com.example.adminedificios.service.Habitacion.HabitacionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class HabitacionImpl implements HabitacionService {

    @Autowired
    HabitacionRepository habitacionRepository;
    @Override
    public Habitacion create(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Habitacion> getAll() {
        return habitacionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Habitacion getById(BigDecimal id) {
        return habitacionRepository.findById(id).orElse(null);
    }

    @Override
    public Habitacion update(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    @Override
    public Habitacion delete(BigDecimal id) {
        Habitacion habitacion = getById(id);
        if (habitacion != null) {
            habitacionRepository.deleteById(id);
            return habitacion;
        }
        return null;
    }
}
