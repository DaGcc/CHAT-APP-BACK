package com.example.chat.app.back.controller;

import com.example.chat.app.back.service.CRUD;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class ControllerCommons<T, S extends CRUD<T>> {

    @Autowired
    protected S service;

    /**
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<?> listarTodos() {
        List<T> lt = this.service.leerTodos();
        return ResponseEntity.ok(lt);
    }

    /**
     *
     * @param t
     * @return
     */
    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody T t) {
        T tbd = this.service.crear(t);
        return ResponseEntity.status(HttpStatus.CREATED).body(tbd);
    }

    /**
     *
     * @param idEntity
     * @return
     */
    @GetMapping("/{idEntity}")
    public ResponseEntity<?> leerPorId(@PathVariable("idEntity") Long idEntity) {
        Optional<T> oT = this.service.leerPorId(idEntity);
//        if (oT.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
//                    body("RECURSO DE ID: " + idEntity + " NO ENCONTRADO");
//        }
//        return ResponseEntity.ok(oT.get());

        return oT.isPresent() ? ResponseEntity.ok(oT.get()) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("RECURSO DE ID: " + idEntity + " NO ENCONTRADO");
    }


    /**
     *
     * @param idEntity
     * @return
     */
    @DeleteMapping("/{idEntity}")
    public ResponseEntity<?> eliminar(@PathVariable("idEntity") Long idEntity) {
        //ya que idEntity es Long(Clase) y no long(primitivo) se puede usar Objects.isNull
        if (idEntity == 0 || Objects.isNull(idEntity)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CARECE DE ID O NO ES VALIDO");
        }
        Optional<T> oT = this.service.leerPorId(idEntity);
        if (oT.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("RECURSO DE ID: " + idEntity + " NO ENCONTRADO");
        }
        this.service.eliminar(idEntity);
        return ResponseEntity.noContent().build();
    }
    
    
    
        /**
     *
     * @param t
     * @param idEntity
     * @return
     */

//    @PutMapping("/{idEntity}")
//    public ResponseEntity<?> editar(@Valid @RequestBody T t,@PathVariable("idEntity") Long idEntity){
//        
//        if(idEntity==0 || idEntity==null){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FALTA ID"); 
//        }
//        
//        Optional<T> oT = this.service.leerPorId(idEntity);
//        if(oT.isPresent()){
//            T tbd  = oT.get();
//            //...
//        }
//        return ResponseEntity.status(HttpStatus.CREATED).body(""); 
//    }
//    
//    

}
