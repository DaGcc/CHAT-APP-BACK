/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.chat.app.back.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@IdClass(ChatUsuarioFK.class)
@Table(name="chat_usuario")
public class ChatUsuario implements Serializable{
    
    @Id
    private Chat chat;
    
    @Id
    private Usuario usuario;
    
    
    @Column(name="scope_user")
    private String scopeUser;
   
    @JsonSerialize(using=ToStringSerializer.class)
    private LocalDateTime fechaUnion;

    
    
    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getScopeUser() {
        return scopeUser;
    }

    public void setScopeUser(String scopeUser) {
        this.scopeUser = scopeUser;
    }

    public LocalDateTime getFechaUnion() {
        return fechaUnion;
    }

    public void setFechaUnion(LocalDateTime fechaUnion) {
        this.fechaUnion = fechaUnion;
    }
    
    
      
}
