package com.example.CinemaCity.Services;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

    @Service
    public class EmailService {
        private final JavaMailSender mailSender;

        public EmailService(JavaMailSender mailSender) {
            this.mailSender = mailSender;
        }

        public void sendEmail(String to, String subject, String content) {
            try {
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setTo(to);
                helper.setSubject(subject);
                helper.setText(content, true);
                mailSender.send(message);
                System.out.println("E-mail trimis cu succes către " + to);
            } catch (Exception e) {
                System.err.println("Eroare la trimiterea e-mailului: " + e.getMessage());
            }
        }
    }
