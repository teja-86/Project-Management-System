package com.aryan;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AppConfigTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void  testPasswordEncoderBean() {
        PasswordEncoder passwordEncoder = context.getBean(PasswordEncoder.class);
        assertNotNull(passwordEncoder);
    }

    @Test
    public void testSecurityChainBean() {
        Object securityChain = context.getBean("springSecurityFilterChain");
        assertNotNull(securityChain);
    }
}
