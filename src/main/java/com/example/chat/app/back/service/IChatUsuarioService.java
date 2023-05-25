package com.example.chat.app.back.service;

import com.example.chat.app.back.model.Chat;
import com.example.chat.app.back.model.ChatUsuario;
import com.example.chat.app.back.model.Usuario;
import java.util.List;


public interface IChatUsuarioService extends CRUD<ChatUsuario>{
    List<Chat> listarChatDeUsuario( Long idUsuario );
    List<Usuario> listarUsuariosDeChat( Long idChat );
}
