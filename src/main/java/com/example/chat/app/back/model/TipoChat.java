/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.chat.app.back.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipo_chat")
public class TipoChat  implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoChat;
       
    @Column(name="nombre", nullable = true,length = 25)
    private String nombre;

    public TipoChat() {
    }

    
    public TipoChat(Long idTipoChat, String nombre) {
        this.idTipoChat = idTipoChat;
        this.nombre = nombre;
    }
    
    public Long getIdTipoChat() {
        return idTipoChat;
    }

    public void setIdTipoChat(Long idTipoChat) {
        this.idTipoChat = idTipoChat;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
