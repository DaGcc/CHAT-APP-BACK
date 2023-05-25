package com.example.chat.app.back.service;

import com.example.chat.app.back.model.Mensaje;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IMensajeService extends CRUD<Mensaje>{
    Page<Mensaje> listarChatPaginado(Long idChat, Pageable pageable);
}
