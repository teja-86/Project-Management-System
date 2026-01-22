package com.aryan.controller;

import com.aryan.model.entity.Chat;
import com.aryan.model.entity.Message;
import com.aryan.model.entity.User;
import com.aryan.request.MessageRequest;
import com.aryan.service.MessageService;
import com.aryan.service.ProjectService;
import com.aryan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody MessageRequest request) throws Exception {

        User user = userService.findUserById((request.getSenderId()));

        Chat chat = projectService.getProjectById(request.getProjectId()).getChat();
        if (chat == null) {
            throw new Exception("Chat not Found!");
        }
        Message sendMessage = messageService.sendMessage(request.getSenderId(),
                request.getProjectId(), request.getContent());

        return ResponseEntity.ok(sendMessage);
    }

    @GetMapping("/chat/{projectId}")
    public ResponseEntity<List<Message>> getMessagesByChatId(@PathVariable Long projectId) throws Exception {
        List<Message> messages = messageService.getMessagesByProjectId(projectId);

        return ResponseEntity.ok(messages);
    }

}
