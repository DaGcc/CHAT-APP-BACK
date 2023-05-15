/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.chat.app.back.controller;

import com.example.chat.app.back.model.Usuario;
import com.example.chat.app.back.service.IUsuarioService;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController extends ControllerCommons<Usuario, IUsuarioService> {

    @PutMapping
    public ResponseEntity<?> editar(@Valid @RequestBody Usuario entity) {

        if (Objects.isNull(entity.getIdUsuario()) || entity.getIdUsuario() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CARECE DE ID O NO ES VALIDO");
        }
        
        Optional<Usuario> oEntity = this.service.leerPorId(entity.getIdUsuario());
        if (oEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("RECURSO NO ENCONTRADO");
        }
        
        Usuario entityBD = this.service.modificar(entity);
        return ResponseEntity.ok(entityBD);
    }

}
