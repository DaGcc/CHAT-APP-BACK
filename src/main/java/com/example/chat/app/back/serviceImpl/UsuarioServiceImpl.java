package com.example.chat.app.back.serviceImpl;

import com.example.chat.app.back.model.Usuario;
import com.example.chat.app.back.repo.IUsuarioRepo;
import com.example.chat.app.back.service.IUsuarioService;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl extends ServiceImplCommons<Usuario,IUsuarioRepo> implements IUsuarioService{

    @Transactional(readOnly=true)
    @Override
    public Optional<Usuario> encontrarUsuarioPorEmail(String email) {
        return this.repo.encontrarUsuarioPorEmail(email);
    }

    @Transactional(readOnly = true)
    @Override
    public Usuario  encontrarUsuarioPorEmilPorDniPorUsername(String valor) {
        return this.repo.encontrarUsuarioPorEmilPorDniPorUsername(valor);
    }
 
}
