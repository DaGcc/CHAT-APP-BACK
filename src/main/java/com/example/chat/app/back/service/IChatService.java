package com.example.chat.app.back.service;

import com.example.chat.app.back.DTO.ChatDTO;
import com.example.chat.app.back.model.Chat;


public interface IChatService extends CRUD<Chat>{
    public Chat registroEspecialChatDTO (ChatDTO chatDTO); 
}
