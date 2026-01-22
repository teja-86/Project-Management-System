package com.aryan;

import com.aryan.model.entity.Invitation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InvitationTest {

    @Test
    public void testInvitation(){
        Invitation invitation = new Invitation();
        invitation.setId(1L);
        invitation.setProjectId(1L);
        invitation.setToken("Token 1");
        invitation.setEmail("test@example.com");
        invitation.setProjectId(1L);

        assertNotNull(invitation);
        assertEquals(1L, invitation.getId());
        assertEquals("Token 1", invitation.getToken());
        assertEquals("test@example.com", invitation.getEmail());
        assertEquals(1L, invitation.getProjectId());
    }
}
