package com.example.adminedificios.service.Huesped.HuespedImpl;

import com.example.adminedificios.model.Huesped;
import com.example.adminedificios.repository.HuespedRepository;
import com.example.adminedificios.service.Huesped.HuespedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class HuespedImpl implements HuespedService {

    @Autowired
    HuespedRepository huespedRepository;

    @Override
    public Huesped save(Huesped huesped) {
        return huespedRepository.save(huesped);
    }

    @Override
    public List<Huesped> getAll() {
        return huespedRepository.findAll();
    }

    @Override
    public Huesped get(BigDecimal id) {
        return huespedRepository.findById(id).orElse(null);
    }

    @Override
    public Huesped update(Huesped huesped) {
        Huesped huespedUpdate = get(huesped.getId());
        if(huespedUpdate != null) {
            huespedRepository.save(huesped);
        }
        return huespedUpdate;
    }

    @Override
    public Huesped delete(BigDecimal id) {
        Huesped huespedUpdate = get(id);
        if(huespedUpdate != null) {
            huespedRepository.deleteById(id);
        }
        return huespedUpdate;
    }
}
