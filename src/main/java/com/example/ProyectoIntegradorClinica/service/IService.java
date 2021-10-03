package com.example.ProyectoIntegradorClinica.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IService<T> {
    T buscar(Integer id);
    T crear(T t);
    T actualizar(T t);
    void eliminar(Integer id);
    List<T> consultarTodos();

}
