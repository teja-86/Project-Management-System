package com.aryan.service.Implementation;

import com.aryan.model.entity.Chat;
import com.aryan.repository.ChatRepo;
import com.aryan.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepo chatRepo;

    @Override
    public Chat createChat(Chat chat) {
        if (chat == null) {
            throw new IllegalArgumentException("Chat object cannot be null");
        }
        return chatRepo.save(chat);
    }

}
