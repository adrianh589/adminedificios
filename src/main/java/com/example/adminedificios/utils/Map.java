package com.example.adminedificios.utils;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
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
