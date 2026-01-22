package com.aryan.service.Implementation;

import com.aryan.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmailWithToken(String userEmail, String link) throws MessagingException {

        if (userEmail == null || userEmail.trim().isEmpty()) {
            throw new IllegalArgumentException("User email cannot be null or empty");
        }
        if (link == null || link.trim().isEmpty()) {
            throw new IllegalArgumentException("Link cannot be null or empty");
        }

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        String subject = "Join Project Team Invitation";
        String text = "Click the Link to join Project Team: " + link;

        helper.setSubject(subject);
        helper.setText(text, true);
        helper.setTo(userEmail);

        try {
            javaMailSender.send(mimeMessage);
        }
        catch (MailException e) {
            throw new MailSendException("Failed to send Email!");
        }
    }

}
