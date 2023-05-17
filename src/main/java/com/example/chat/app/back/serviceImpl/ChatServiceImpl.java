package com.example.chat.app.back.serviceImpl;

import com.example.chat.app.back.DTO.ChatDTO;
import com.example.chat.app.back.model.Chat;
import com.example.chat.app.back.repo.IChatRepo;
import com.example.chat.app.back.repo.IChatUsuarioRepo;
import com.example.chat.app.back.service.IChatService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChatServiceImpl extends ServiceImplCommons<Chat,IChatRepo> implements IChatService{

    @Autowired
    private IChatUsuarioRepo repoCU;
    
    @Override
    @Transactional
    public Chat registroEspecialChatDTO(ChatDTO chatDTO) {
        //de manera directa, pues ya configuramos en la clase un metodo que registrara al padre a cada mensaje
        repo.save(chatDTO.getChat());//le da un id de manera interna luego de su save
        chatDTO.getListaChatUsuario().forEach(cu -> {
            System.out.println(cu.getChat().getIdChat());
//            cu.setChat(chatDTO.getChat()); //)ya lo hace a nivel de clase ChatDTO
            this.repoCU.registrar(cu.getFechaUnion(), cu.getScopeUser(), cu.getChat().getIdChat(),cu.getUsuario().getIdUsuario());
        });
        return chatDTO.getChat();
    }
    
   /*
        @Id
    private Chat chat;
    
    @Id
    private Usuario usuario;
    
    
    @Column(name="scope_user")
    private String scopeUser;
   
    @JsonSerialize(using=ToStringSerializer.class)
    private LocalDateTime fechaUnion;
    */
 
}
