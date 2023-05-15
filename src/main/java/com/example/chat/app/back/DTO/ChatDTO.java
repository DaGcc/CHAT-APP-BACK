package com.example.chat.app.back.DTO;

import com.example.chat.app.back.model.Chat;
import java.util.List;

public class ChatDTO {
    
    private Chat chat;
   
    private List<ChatUsuarioDTO> listaChatUsuarioDTO;

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public List<ChatUsuarioDTO> getListaChatUsuarioDTO() {
        return listaChatUsuarioDTO;
    }

    public void setListaChatUsuarioDTO(List<ChatUsuarioDTO> listaChatUsuarioDTO) {
        this.listaChatUsuarioDTO = listaChatUsuarioDTO;
    }
    
    
    
}
