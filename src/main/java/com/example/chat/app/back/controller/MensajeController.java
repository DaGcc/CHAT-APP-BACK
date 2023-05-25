/*
CORS -rest y websocket
registro en el service impl de chat
 */
package com.example.chat.app.back.controller;

import com.example.chat.app.back.exception.ModelNotFoundException;
import com.example.chat.app.back.model.Mensaje;
import com.example.chat.app.back.model.Usuario;
import com.example.chat.app.back.service.IMensajeService;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/mensajes")
public class MensajeController extends ControllerCommons<Mensaje, IMensajeService> {


    @PutMapping
    public ResponseEntity<?> editar(@Valid @RequestBody Mensaje entity) {

        if (Objects.isNull(entity.getIdMensaje()) || entity.getIdMensaje() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CARECE DE ID O NO ES VALIDO");
        }
        Optional<Mensaje> oEntity = this.service.leerPorId(entity.getIdMensaje());
        if (oEntity.isEmpty()) {
            throw new ModelNotFoundException("RECURSO NO ENCONTRADO PARA SU EDICION");
        }
        Mensaje entityBD = this.service.modificar(entity);
        return ResponseEntity.ok(entityBD);
    }
    
    //http://localhost:8080/mensajes/paginado?idChat=2&page=2&size=3
    @GetMapping("/paginado")
    public ResponseEntity<?>  listarChatPaginado(@RequestParam("idChat") Long idChat, Pageable pageable) {
        System.out.println(idChat);
        Page page = this.service.listarChatPaginado(idChat, pageable);
       return  ResponseEntity.ok(page);
    }

 
}
