package com.example.chat.app.back.DTO;

import com.example.chat.app.back.model.Chat;
import com.example.chat.app.back.model.ChatUsuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChatDTO implements Serializable {

//    @JsonIgnoreProperties(value = {"listaMensajes", "hibernateLazyInitializer", "handler"})
    private Chat chat;

    private List<ChatUsuario> listaChatUsuario;

    public Chat getChat() {
        return chat;
    }

    public ChatDTO() {
        /**
         * importante pues puede dar error como : "JSON parse error: Cannot
         * invoke \"java.util.List.add(Object)\" because
         * \"this.listaChatUsuario\" is null;
         */
        this.listaChatUsuario = new ArrayList<>();
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public List<ChatUsuario> getListaChatUsuario() {
        return listaChatUsuario;
    }

    public void setListaChatUsuario(List<ChatUsuario> listaChatUsuario) {
        listaChatUsuario.forEach(this::addChat);
    }

    public void addChat(ChatUsuario chatUsuario) {
        chatUsuario.setChat(this.chat);
        this.listaChatUsuario.add(chatUsuario);
    }

}
