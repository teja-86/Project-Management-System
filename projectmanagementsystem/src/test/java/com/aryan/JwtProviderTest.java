package com.aryan;

import com.aryan.config.JwtProvider;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class JwtProviderTest {

    @Test
    public void testGenerateTokenAndGetEmailFromToken() {

        Authentication auth = mock(Authentication.class);

        when(auth.getName()).thenReturn("test@example.com");

        String jwt = JwtProvider.generateToken(auth);

        assertEquals(3, jwt.split("\\.").length, "The generated JWT token should have three parts");

        String email = JwtProvider.getEmailFromToken("Bearer " + jwt);

        assertEquals("test@example.com", email, "The extracted email should match the one used to generate the token");

    }
}
