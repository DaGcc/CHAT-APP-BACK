package com.example.chat.app.back.service;

import java.util.List;
import java.util.Optional;

public interface CRUD<T> {

    List<T> leerTodos();

    Optional<T> leerPorId(long idEntity);

    T crear(T t);

    T modificar(T t);

    void eliminar(long idEntity);

}
