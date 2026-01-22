package com.aryan;

import com.aryan.model.entity.Chat;
import com.aryan.model.entity.Message;
import com.aryan.model.entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MessageTest {

    @Test
    public void testMessage() {
        Message message = new Message();
        message.setId(1L);
        message.setContent("Message 1");
        message.setCreatedAt(LocalDateTime.now());
        message.setChat(new Chat());
        message.setSender(new User());

        assertNotNull(message);
        assertEquals(1L, message.getId());
        assertEquals("Message 1", message.getContent());
        assertNotNull(message.getCreatedAt());
        assertNotNull(message.getChat());
        assertNotNull(message.getSender());
    }
}
