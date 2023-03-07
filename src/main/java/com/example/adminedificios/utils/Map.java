package com.example.adminedificios.utils;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class Map {
    // Funcion para mapear objetos con 2 o mas propiedades ModelMapper
    public static <S, T> List<T> mapAll(List<S> source, Class<T> targetClass, ModelMapper modelMapper) {
        List<T> list = new ArrayList<>();
        for (S element : source) {
            list.add(modelMapper.map(element, targetClass));
        }
        return list;
    }
}
