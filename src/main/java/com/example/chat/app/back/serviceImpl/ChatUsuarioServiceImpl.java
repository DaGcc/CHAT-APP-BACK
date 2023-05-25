package com.example.chat.app.back.serviceImpl;

import com.example.chat.app.back.model.Chat;
import com.example.chat.app.back.model.ChatUsuario;
import com.example.chat.app.back.model.Usuario;
import com.example.chat.app.back.repo.IChatUsuarioRepo;
import com.example.chat.app.back.service.IChatUsuarioService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatUsuarioServiceImpl implements IChatUsuarioService{

    @Autowired 
    private IChatUsuarioRepo repo; 
    
    @Override
    public List<ChatUsuario> leerTodos() {
        return this.repo.findAll();
    }

    @Override
    public Optional<ChatUsuario> leerPorId(long idEntity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ChatUsuario crear(ChatUsuario t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ChatUsuario modificar(ChatUsuario t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(long idEntity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Chat> listarChatDeUsuario(Long idUsuario) {
        return this.repo.listarChatDeUsuario(idUsuario);
    }

    @Override
    public List<Usuario> listarUsuariosDeChat(Long idChat) {
        return this.repo.listarUsuariosDeChat(idChat);
    }
  
} 
