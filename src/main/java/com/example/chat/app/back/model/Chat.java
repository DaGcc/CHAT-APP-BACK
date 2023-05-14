/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.chat.app.back.model;

import java.util.List;

//@Entity
//@Table(name="usuario")
public class Chat {

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChat;

    private String nombreChat;

    private String tipo;

//    @ManyToMany(fetch=FetchType.Lazy)
//    @JoinTable(name = "chat_usuario", joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "idUsuario"),
//            inverseJoinColumns = @JoinColumn(name = "id_chat", referencedColumnName = "idChat"),
//            uniqueConstraints = {
//                @UniqueConstraint(columnNames = {"id_usuario", "id_rol"})})
    private List<Usuario> listaUsuarios;
    
    
    
//    @OneToMany(mappedBy="chat",cascade=CascadeType.ALL,fetch=FetchType.EAGER, orphanremoval=true);
    private List<Mensaje> listaMensajes;
    
    
    
    
    

}
