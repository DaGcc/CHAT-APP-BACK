

package com.example.chat.app.back.controllerWebSocket;

import com.example.chat.app.back.DTO.ChatDTO;
import com.example.chat.app.back.model.Chat;
import com.example.chat.app.back.service.IChatService;
import com.example.chat.app.back.service.IChatUsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


@Controller
public class ChatControllerWS {
    
    @Autowired
    private IChatService service;
        
    @Autowired
    private SimpMessagingTemplate destino;
    
    
    @MessageMapping("/chat-actualizado")
    public void actualizarChatDeUser(ChatDTO chatDTO) {
        
        Chat chat = this.service.registroEspecialChatDTO(chatDTO);
        if(chat!=null){
            chatDTO.getListaChatUsuario().forEach(cu -> {
               destino.convertAndSend("/chat/chat-actualizado/"+cu.getUsuario().getIdUsuario(),"actualizado"); 
            });
        }
    
    }
    
}
