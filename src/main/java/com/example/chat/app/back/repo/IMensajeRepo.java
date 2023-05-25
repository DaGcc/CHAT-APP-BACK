package com.example.chat.app.back.repo;

import com.example.chat.app.back.model.Mensaje;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IMensajeRepo extends JpaRepository<Mensaje, Long> {

//    @Query("FROM Mensaje m JOIN FETCH m.chat c WHERE c.idChat=:idChat")
    @Query("FROM Mensaje m WHERE m.chat.idChat=:idChat")
    Page<Mensaje> listarChatPaginado(@Param("idChat") Long idChat, Pageable pageable);
}
