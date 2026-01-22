package com.aryan.repository;

import com.aryan.model.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepo extends JpaRepository<Chat, Long> {

}
