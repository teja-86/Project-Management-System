package com.aryan.repository;

import com.aryan.model.entity.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationRepo extends JpaRepository<Invitation, Long> {

    Invitation findByToken(String token);

    Invitation findByEmail(String email);
    
}
