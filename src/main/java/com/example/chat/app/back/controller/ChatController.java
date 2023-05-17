/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.chat.app.back.controller;

import com.example.chat.app.back.DTO.ChatDTO;
import com.example.chat.app.back.model.Chat;
import com.example.chat.app.back.service.IChatService;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chats")
public class ChatController extends ControllerCommons<Chat, IChatService> {

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
    public ResponseEntity<?> editar( @RequestBody ChatDTO dto) {
        Chat chatBD = this.service.registroEspecialChatDTO(dto);
        return ResponseEntity.ok(chatBD);
    }

}
