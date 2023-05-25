/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.chat.app.back.controller;

import com.example.chat.app.back.exception.ModelNotFoundException;
import com.example.chat.app.back.model.Usuario;
import com.example.chat.app.back.service.IUsuarioService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/usuarios")
public class UsuarioController extends ControllerCommons<Usuario, IUsuarioService> {

    private String[] colores = {"#ff8ed5","#f03c02","#ff00b1","#f275ff","#ff759d",
    "#bd8eff","#ff4da2","#b275c9","#9520c1","#661eed","#f03c02","#ff6e4a","#0033ff","#ff0000",
    "#ff8a03","#0091ff","#4ca9ba","#000","#cbae0a"};
    private String[] coloresHombre = {"#661eed","#f03c02","#ff6e4a","#0033ff","#ff0000",
    "#ff8a03","#0091ff","#4ca9ba","#000","#cbae0a"};
    private String[] coloresMujer = {"#ff8ed5","#f03c02","#ff00b1","#f275ff","#ff759d",
    "#bd8eff","#ff4da2","#b275c9","#9520c1"};
    

    @PostMapping("/guardado")
    public ResponseEntity<?> guardar(@Valid @RequestBody Usuario t) {
   
        switch(t.getGenero()){
            case "M": {
               t.setColor(coloresHombre[new Random().nextInt(colores.length)]);
                break;
            }
            case "F": {
                t.setColor(coloresMujer[new Random().nextInt(colores.length)]);
                break;
            }
            default :
                t.setColor(colores[new Random().nextInt(colores.length)]);
                    break;
            
        }
        return super.guardar(t);
    }

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

    //mala practica ->seria mandar a buscarlo por pass y luego comparar su pass?
    @GetMapping("/buscado")
    public ResponseEntity<?> editar(@RequestParam("email") String email) {
        Optional<Usuario> oEntity = this.service.encontrarUsuarioPorEmail(email);
        if (oEntity.isEmpty()) {
            throw new ModelNotFoundException("EL EMAIL: " + email + " NO ESTA REGISTRADO");
        }

        Usuario entity = oEntity.get();
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/buscar-usuario")
    public ResponseEntity<?> encontrarUsuarioPorEmilPorDniPorUsername(
            @RequestParam("valor") String valor) {
        Usuario entity = this.service.encontrarUsuarioPorEmilPorDniPorUsername(valor);
        if (Objects.isNull(entity)) {
            throw new ModelNotFoundException("USUARIO CON CREDENCIAL: " + valor + ", NO ENCONTRADO");
        }
        return ResponseEntity.ok(entity);
    }

}
