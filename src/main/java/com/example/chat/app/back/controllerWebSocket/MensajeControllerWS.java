/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.chat.app.back.controllerWebSocket;

import com.example.chat.app.back.model.Mensaje;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 *
 * @author gutie
 */
@Controller
public class MensajeControllerWS {
    
    @Autowired
    private  SimpMessagingTemplate webSocket;
    
    //res api -> pides y te da
    //websocket -> mandas , te da en base subcrito
    @MessageMapping("/mensaje") //<-cliente angular
    @SendTo("/chat/mensaje") // -> subsc.
    public Mensaje recibirMensaje(Mensaje mensaje) {
//        mensaje.setFecha(new Date().getTime());
//        if (mensaje.getTipo().equals("NUEVO_USUARIO")) {
//            mensaje.setTexto("nuevo usuario");
//        }
        return mensaje;
    }
    @MessageMapping("/escribiendo")
    @SendTo("/chat/escribiendo")//el sentTo envia al mentodo /chat/mensaje lo que se retorna aqui
    public String estaEscribiendo(String username) {
        return username + " está escribiendo....";
    }

//    @MessageMapping("/historial")
////    @SendTo("/chat/historial")
//    public void recibirLosUltimos10Mensajes(String clienteId) {
//        List<Mensaje> mensajes = service.obtenerUltimos10Mnesaje();
//        System.out.println(mensajes.size()); 
//        webSocket.convertAndSend("/chat/historial/"+clienteId, mensajes); 
//    }
}
