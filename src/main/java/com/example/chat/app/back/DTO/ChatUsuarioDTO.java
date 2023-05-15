package com.example.chat.app.back.DTO;

import com.example.chat.app.back.model.Usuario;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.time.LocalDateTime;


public class ChatUsuarioDTO {
    
    private Usuario usuario;
    
   
    private String scopeUser;
    
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime fechaUnion;

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
