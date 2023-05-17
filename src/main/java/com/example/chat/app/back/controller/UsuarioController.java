/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.chat.app.back.controller;

import com.example.chat.app.back.model.Usuario;
import com.example.chat.app.back.service.IUsuarioService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController extends ControllerCommons<Usuario, IUsuarioService> {

    //edicion con img
    @PutMapping(value = "/con-img", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> editarConImg(@Valid Usuario entity,
            @RequestParam("archivo") MultipartFile archivo) throws IOException {
        Optional<Usuario> oUsuario = this.service.leerPorId(entity.getIdUsuario());
        if (oUsuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("RECURSO NO ENCONTRADO");
        }
        Usuario dataAnterior = oUsuario.get();
        /*Verificamos si viene imagen o no (OJO: si no viene lo dejamos con su im anterior) 
        y para quitar la img crear otro controlador especificamente para quitar img*/
        if (!archivo.isEmpty()) {
            //verificamos si ya tiene asociada una img
            Path rutaArchivoAnterior = Paths.get("imgs").resolve(dataAnterior.getRutaFoto()).toAbsolutePath();
            if (Files.exists(rutaArchivoAnterior, new LinkOption[]{LinkOption.NOFOLLOW_LINKS})) {
                Files.delete(rutaArchivoAnterior);
            }

            String nombreArchivoNuevo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
            Path rutaArchivoNuevo = Paths.get("imgs").resolve(nombreArchivoNuevo).toAbsolutePath();
            var inputStream = archivo.getInputStream();
            Files.copy(inputStream, rutaArchivoNuevo);
            inputStream.close();
            entity.setRutaFoto(nombreArchivoNuevo);
        }

        Usuario entityBD = this.service.modificar(entity);
        return ResponseEntity.status(HttpStatus.OK).body(entityBD);
    }

    //edicion normal
    @PutMapping
    public ResponseEntity<?> editar(@Valid @RequestBody Usuario entity) throws IOException {

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
