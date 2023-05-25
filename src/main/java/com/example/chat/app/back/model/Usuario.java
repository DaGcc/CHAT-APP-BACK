package com.example.chat.app.back.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Size(min = 3, max = 15)
    @Column(name = "username", nullable = false, length = 15)
    private String username;

    @Column(name = "color", nullable = true, length = 15)
    private String color;

    @Column(name = "rutaFoto", nullable = true)
    private String rutaFoto;
    
    @Size(min=1,max=1, message="SOLO SE ACEPTA 1 CARACTER EN EL GENERO")
    @Column(name = "genero",length=1, nullable = false)
    private String genero;
    
    @Column(name="estado")
    private boolean estado;
    
    @NotNull
    @Email(message = "INGRESE UN CORREO ELECTRÓNICO VALIDO")
    @Size(min = 4, max = 120, message = "EL CORREO ELECTRONICO DEBE CONTENER COMO MINIMO 4 CARACTERES Y UN MAXIMO DE 120")
    @Column(name = "email", nullable = false, unique = true, length = 120)
    private String email;

    
    @Size(min = 8, max = 8, message = "EL DNI DEBE CONTENER COMO MINIMO Y MAXIMO 8 CARACTERES")
    @Column(name = "dni", nullable = false, unique = true, length = 8)
    private String dni;

    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    /*CONSTRUCTORES*/
    public Usuario() {
    }



    /*METODOS GET Y SET*/
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

  
    
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

    
    
    
    /*PARA COMPARAR OBJS DE LA MISMA CLASE*/
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.idUsuario);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.idUsuario, other.idUsuario)) {
            return false;
        }
        return true;
    }
}
