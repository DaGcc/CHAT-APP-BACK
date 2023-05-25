package com.example.chat.app.back.service;

import com.example.chat.app.back.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface IUsuarioService extends CRUD<Usuario> {

    Optional<Usuario> encontrarUsuarioPorEmail(String email);

    Usuario  encontrarUsuarioPorEmilPorDniPorUsername(String valor);
}
