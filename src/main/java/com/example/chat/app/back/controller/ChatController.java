/*
CORS -rest y websocket
registro en el service impl de chat
 */
package com.example.chat.app.back.controller;

import com.example.chat.app.back.DTO.ChatDTO;
import com.example.chat.app.back.model.Chat;
import com.example.chat.app.back.model.Usuario;
import com.example.chat.app.back.service.IChatService;
import com.example.chat.app.back.service.IChatUsuarioService;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/chats")
public class ChatController extends ControllerCommons<Chat, IChatService> {

    @Autowired
    private IChatUsuarioService serviceCU;

    @PutMapping
    public ResponseEntity<?> editar(@Valid @RequestBody Chat entity) {

        if (Objects.isNull(entity.getIdChat()) || entity.getIdChat() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CARECE DE ID O NO ES VALIDO");
        }

        Optional<Chat> oEntity = this.service.leerPorId(entity.getIdChat());
        if (oEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("RECURSO NO ENCONTRADO");
        }

        Chat entityBD = this.service.modificar(entity);
        return ResponseEntity.ok(entityBD);
    }

    @PostMapping("/registro-especial")
    public ResponseEntity<?> editar(@RequestBody ChatDTO dto) {
        Chat chatBD = this.service.registroEspecialChatDTO(dto);
        return ResponseEntity.ok(chatBD);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<?> listarChatDeUsuario(@PathVariable("idUsuario") Long idUsuario) {
        List<Chat> ltChat = this.serviceCU.listarChatDeUsuario(idUsuario);
        return ResponseEntity.ok(ltChat);
    }
    
    @GetMapping("/especial/{idChat}")
    public ResponseEntity<?> listarUsuariosDeChat(@PathVariable("idChat") Long idChat) {
        List<Usuario> ltChat = this.serviceCU.listarUsuariosDeChat(idChat);
        return ResponseEntity.ok(ltChat);
    }
    
}
