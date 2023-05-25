package com.example.chat.app.back.serviceImpl;

import com.example.chat.app.back.model.Mensaje;
import com.example.chat.app.back.repo.IMensajeRepo;
import com.example.chat.app.back.service.IMensajeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MensajeServiceImpl extends ServiceImplCommons<Mensaje,IMensajeRepo> implements IMensajeService{

    @Transactional(readOnly = true)
    @Override
    public Page<Mensaje> listarChatPaginado(Long idChat, Pageable pageable) {
        return this.repo.listarChatPaginado(idChat, pageable);
    }

}
