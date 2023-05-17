package com.example.chat.app.back.repo;


import com.example.chat.app.back.model.ChatUsuario;
import com.example.chat.app.back.model.ChatUsuarioFK;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IChatUsuarioRepo extends JpaRepository<ChatUsuario, ChatUsuarioFK>{
   
    
    @Modifying
    @Query(value ="INSERT INTO chat_usuario VALUES(:fechaUnion,:scopeUser,:id_user,:id_chat)",nativeQuery = true)
    Integer registrar(@Param("fechaUnion")LocalDateTime fechaUnion,
            @Param("scopeUser")String scopeUser,
            @Param("id_chat")Long id_chat,
            @Param("id_user")Long id_user);
}
