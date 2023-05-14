/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.chat.app.back.model;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;



//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Email;
//
//@Entity
//@Table(name="usuario")
public class Usuario {
    
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    
    private String username;
    
    private String color;
   
//    @NotNull
//    @Email(message="INGRESE UN CORREO ELECTRÓNICO VALIDO")
    private String email;
    
//    @NotNull
    private String dni;
    
    
//    @NotNull
    private String password;

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
   
    
    
    
}

