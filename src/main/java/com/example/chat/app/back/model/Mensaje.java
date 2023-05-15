package com.example.chat.app.back.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@Entity
@Table(name="mensaje")
public class Mensaje {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMensaje;
    
    @Size(min = 0, max = 100)
    @Column(name = "texto", nullable = false, length = 100)
    private String texto;
       
    @ManyToOne(fetch= FetchType.LAZY)
    @JsonIgnoreProperties(value={"listaMensajes","hibernateLazyInitializer", "handler"})
    @JoinColumn(name="id_chat")
    private Chat chat;
    
    private Long fecha;

    
}
