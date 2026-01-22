package com.aryan.service.Implementation;

import com.aryan.model.entity.Invitation;
import com.aryan.repository.InvitationRepo;
import com.aryan.service.EmailService;
import com.aryan.service.InvitationService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class InvitationServiceImpl implements InvitationService {

    @Autowired
    private InvitationRepo invitationRepo;

    @Autowired
    private EmailService emailService;

    @Override
    public void sendInvitation(String email, Long projectId) throws MessagingException {

        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (projectId == null || projectId <= 0) {
            throw new IllegalArgumentException("Invalid project ID");
        }

        String invitationToken = UUID.randomUUID().toString();

        Invitation invitation = new Invitation();
        invitation.setEmail(email);
        invitation.setProjectId(projectId);
        invitation.setToken(invitationToken);

        invitationRepo.save(invitation);

        String invitationLink = "http://localhost:5173/accept_invitation?token=" + invitationToken;
        emailService.sendEmailWithToken(email, invitationLink);
    }

    @Override
    public Invitation acceptInvitation(String token, Long userId) throws Exception {
        Invitation invitation = invitationRepo.findByToken(token);
        if (invitation == null) {
            throw new Exception("Invalid Invitation Token!");
        }
        return invitation;
    }

    @Override
    public String getTokenByUserMail(String userEmail) {
        Invitation invitation = invitationRepo.findByEmail(userEmail);
        if (invitation == null) {
            throw new IllegalArgumentException("No invitation found for the provided email: " + userEmail);
        }
        return invitation.getToken();
    }

    @Override
    public void deleteToken(String token) {
        Invitation invitation = invitationRepo.findByToken(token);
        if (invitation == null) {
            throw new IllegalArgumentException("No invitation found for the provided token: " + token);
        }
        invitationRepo.delete(invitation);
    }

}
