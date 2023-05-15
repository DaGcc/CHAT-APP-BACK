package com.example.chat.app.back.repo;

import com.example.chat.app.back.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepo extends JpaRepository<Usuario, Long>{
    
}
