package com.example.chat.app.back.repo;

import com.example.chat.app.back.model.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IUsuarioRepo extends JpaRepository<Usuario, Long> {

    @Query("FROM Usuario u WHERE u.email =:email")
    Optional<Usuario> encontrarUsuarioPorEmail(@Param("email") String email);

    @Query("FROM Usuario u WHERE  LOWER(u.email)= LOWER(:valor) or LOWER(u.username)=LOWER(:valor) "
            + "or u.dni = :valor")
    Usuario encontrarUsuarioPorEmilPorDniPorUsername(@Param("valor") String valor);

}
