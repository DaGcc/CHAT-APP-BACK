package com.example.chat.app.back.repo;

import com.example.chat.app.back.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IChatRepo extends JpaRepository<Chat, Long>{
    
}
