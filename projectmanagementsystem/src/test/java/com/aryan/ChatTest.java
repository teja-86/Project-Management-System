package com.aryan;

import com.aryan.model.entity.Chat;
import com.aryan.model.entity.Project;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ChatTest {

    @Test
    public void testChat(){
        Chat chat = new Chat();
        chat.setName("Chat 1");
        chat.setProject(new Project());
        chat.setMessage(new ArrayList<>());
        chat.setUsers(new ArrayList<>());

        assertNotNull(chat);
        assertEquals("Chat 1", chat.getName());
        assertNotNull(chat.getProject());
        assertNotNull(chat.getMessage());
        assertNotNull(chat.getUsers());
    }
}
