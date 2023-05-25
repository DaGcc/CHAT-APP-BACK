/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.chat.app.back.controllerWebSocket;

import com.example.chat.app.back.DTO.NotificacionUser;
import com.example.chat.app.back.model.Mensaje;
import com.example.chat.app.back.service.IMensajeService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/*
M: MENSAJE
N: NUEVO USUARIO
*/

@Controller
public class MensajeControllerWS {
    
    @Autowired
    private IMensajeService service;
    
    @Autowired
    private  SimpMessagingTemplate webSocket;
  
    @MessageMapping("/mensaje") //<-cliente angular
//    @SendTo("/chat/mensaje") // -> subsc.
    public void recibirMensaje(Mensaje mensaje) {
        mensaje.setFecha(new Date().getTime());
        if (mensaje.getTipo().equals("N")) {//falta ver la logica ...
//            mensaje.setTexto(mensaje.getUsuario().getUsername()+" AÑADIO A "+);
        }
        mensaje = this.service.crear(mensaje);
        System.out.println(mensaje.getChat().getIdChat());
        webSocket.convertAndSend("/chat/mensaje/"+mensaje.getChat().getIdChat(), mensaje);
    }
    
    
    /*
    N: NUEVO_MENSAJE
    E: ESCRIBIENDO
    */
    @MessageMapping("/notificar")
    public void estaEscribiendo(NotificacionUser notificaciones) {
        String n;
        if(notificaciones.getTipo().equals("E")){
             n = notificaciones.getUsuario().getUsername() + " está escribiendo....";
        }else if(notificaciones.getTipo().equals("N")){
             n = "N";
        }else{
             n = "";
        }
        webSocket.convertAndSend("/chat/notificar/"+notificaciones.getChat().getIdChat(), n);
    }  
}
    