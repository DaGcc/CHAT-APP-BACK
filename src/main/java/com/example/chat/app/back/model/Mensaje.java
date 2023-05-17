package com.example.chat.app.back.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
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
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="id_usuario",foreignKey = @ForeignKey(name="fk_mensaje_usuario"))
    private Usuario usuario;
    
    @Size(min = 0, max = 100)
    @Column(name = "texto", nullable = false, length = 100)
    private String texto;
       
    @ManyToOne(fetch= FetchType.LAZY)
    @JsonIgnoreProperties(value={"listaMensajes","hibernateLazyInitializer", "handler"})
    @JoinColumn(name="id_chat")
    private Chat chat;
    
    @Column(name="fecha")//?
    private Long fecha;

    public Long getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(Long idMensaje) {
        this.idMensaje = idMensaje;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Long getFecha() {
        return fecha;
    }

    public void setFecha(Long fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.idMensaje);
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
        final Mensaje other = (Mensaje) obj;
        if (!Objects.equals(this.idMensaje, other.idMensaje)) {
            return false;
        }
        return true;
    }

    
    

    
}
