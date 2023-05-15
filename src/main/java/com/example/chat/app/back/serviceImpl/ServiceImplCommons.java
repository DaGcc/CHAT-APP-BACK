package com.example.chat.app.back.serviceImpl;

import com.example.chat.app.back.service.CRUD;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public class ServiceImplCommons<T, R extends JpaRepository<T, Long>> implements CRUD<T> {

    @Autowired
    protected R repo;

    @Override
    public List<T> leerTodos() {
        return this.repo.findAll();
    }

    @Override
    public Optional<T> leerPorId(long idEntity) {
        return this.repo.findById(idEntity);
    }

    @Override
    public T crear(T t) {
        return this.repo.save(t);
    }

    @Override
    public T modificar(T t) {
        return this.repo.save(t);
    }

    @Override
    public void eliminar(long idEntity) {
        this.repo.deleteById(idEntity);
    }

}
