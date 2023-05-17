/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.chat.app.back.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ChatUsuarioFK implements Serializable{
    
    @ManyToOne
    @JoinColumn(name="id_chat",
            nullable=false,
            foreignKey = @ForeignKey(name="fk_chat_usuario_chat"))
    private Chat chat;
             
    @ManyToOne
    @JoinColumn(name="id_usuario",
            nullable=false,
            foreignKey=@ForeignKey(name="fk_chat_usuario_usuario"))
    private Usuario usuario;
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.chat);
        hash = 97 * hash + Objects.hashCode(this.usuario);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ChatUsuarioFK other = (ChatUsuarioFK) obj;
        if (!Objects.equals(this.chat, other.chat)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        return true;
    }
    
}
