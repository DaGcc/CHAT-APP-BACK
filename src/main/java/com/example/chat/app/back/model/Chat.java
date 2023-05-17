package com.example.chat.app.back.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChat;

    @Size(min = 3, max = 60, message = "EL NOMBRE DEL CHAT DEBE TENER COMO MINIMO 3 CARACTERES Y COMO MAXIMO 25")
    @Column(name = "nombre", nullable = true, length = 60)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_tipo_chat", foreignKey = @ForeignKey(name = "fk_chat_tipo_chat"))
    private TipoChat tipoChat;

    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime fechaCreacion;

    @JsonIgnoreProperties(value = {"chat", "hibernateLazyInitializer", "handler"})
    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Mensaje> listaMensajes;

    public Chat() {
        this.listaMensajes = new ArrayList<>();
    }

    public Long getIdChat() {
        return idChat;
    }

    public void setIdChat(Long idChat) {
        this.idChat = idChat;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoChat getTipoChat() {
        return tipoChat;
    }

    public void setTipoChat(TipoChat tipoChat) {
        this.tipoChat = tipoChat;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<Mensaje> getListaMensajes() {
        return listaMensajes;
    }

    //sera llamado para deserealizar
    public void setListaMensajes(List<Mensaje> listaMensajes) {
        this.listaMensajes.clear();
        listaMensajes.forEach(this::addMensaje);
    }

    //METODOS PERSONALIZADOS
    //se encarga de setear el valor correspondiente, y no hace falta programar en el service
    public void addMensaje(Mensaje mensaje) {
        mensaje.setChat(this);//falta probar si es al revez o no
        this.listaMensajes.add(mensaje);

    }

    public void removeMensaje(Mensaje mensaje) {
        this.listaMensajes.remove(mensaje);
        mensaje.setChat(null);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.idChat);
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
        final Chat other = (Chat) obj;
        if (!Objects.equals(this.idChat, other.idChat)) {
            return false;
        }
        return true;
    }

}
