package com.aryan.service.Implementation;

import com.aryan.model.entity.Chat;
import com.aryan.model.entity.Message;
import com.aryan.model.entity.User;
import com.aryan.repository.MessageRepo;
import com.aryan.repository.UserRepo;
import com.aryan.service.MessageService;
import com.aryan.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProjectService projectService;

    @Override
    public Message sendMessage(Long senderId, Long projectId, String content) throws Exception {

        if (senderId == null || senderId <= 0) {
            throw new IllegalArgumentException("Invalid sender ID");
        }
        if (projectId == null || projectId <= 0) {
            throw new IllegalArgumentException("Invalid project ID");
        }
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be null or empty");
        }

        User sender = userRepo.findById(senderId).
                orElseThrow(() -> new  Exception("User not Found with ID: " + senderId));

        Chat chat = projectService.getProjectById(projectId).getChat();
        if (chat == null) {
            throw new Exception("Chat not found for project ID: " + projectId);
        }

        Message message = new Message();
        message.setSender(sender);
        message.setChat(chat);
        message.setContent(content);
        message.setCreatedAt(LocalDateTime.now());
        Message savedMessage = messageRepo.save(message);

        chat.getMessage().add(savedMessage);
        return savedMessage;
    }

    @Override
    public List<Message> getMessagesByProjectId(Long projectId) throws Exception {

        if (projectId == null || projectId <= 0) {
            throw new IllegalArgumentException("Invalid project ID");
        }

        Chat chat = projectService.getChatByProjectId(projectId);
        if (chat == null) {
            throw new Exception("Chat not found for project ID: " + projectId);
        }

        return messageRepo.findByChatIdOrderByCreatedAtAsc(chat.getId());
    }

}
