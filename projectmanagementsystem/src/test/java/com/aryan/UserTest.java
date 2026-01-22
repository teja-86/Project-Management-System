package com.aryan;

import com.aryan.model.entity.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserTest {

    @Test
    public void testUser() {
        User user = new User();
        user.setId(1L);
        user.setFullName("User 1");
        user.setEmail("user1@example.com");
        user.setPassword("password");
        user.setAssignedIssues(new ArrayList<>());
        user.setProjectSize(5);

        assertNotNull(user);
        assertEquals(1L, user.getId());
        assertEquals("User 1", user.getFullName());
        assertEquals("user1@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
        assertNotNull(user.getAssignedIssues());
        assertEquals(5, user.getProjectSize());
    }
}
