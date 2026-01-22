package com.aryan.repository;

import com.aryan.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long> {

    List<Message> findByChatIdOrderByCreatedAtAsc(long chatId);

}
