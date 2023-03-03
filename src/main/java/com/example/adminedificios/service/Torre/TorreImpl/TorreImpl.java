package com.example.adminedificios.service.Torre.TorreImpl;

import com.example.adminedificios.model.Torre;
import com.example.adminedificios.repository.TorreRepository;
import com.example.adminedificios.service.Torre.TorreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TorreImpl implements TorreService {

    @Autowired
    private TorreRepository torreRepository;

    @Override
    public List<Torre> findAll() {
        return torreRepository.findAll();
    }

    @Override
    public Torre findById(BigDecimal id) {
        return torreRepository.findById(id).orElse(null);
    }

    @Override
    public Torre save(Torre torre) {
        return torreRepository.save(torre);
    }

    @Override
    public Torre deleteById(BigDecimal id) {
        Torre torre = findById(id);
        if (torre != null){
            torreRepository.delete(torre);
        }
        return torre;
    }
}
