package com.project.ServiceBooking.services;

import com.project.ServiceBooking.data.User;
import com.project.ServiceBooking.data.VerificationToken;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService {
    private final VerificationTokenService verificationTokenService;
    private final TemplateEngine templateEngine;
    private JavaMailSender javaMailSender;

    public EmailService(VerificationTokenService verificationTokenService, TemplateEngine templateEngine, JavaMailSender javaMailSender) {
        this.verificationTokenService = verificationTokenService;
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
    }

    public void sendHtmlMail(User user) throws MessagingException{
        VerificationToken verificationToken=verificationTokenService.findByUser(user);
        //check if user has token
        if(verificationToken!=null){
            String token = verificationToken.getToken();
            Context context = new Context();
            context.setVariable("title","Verify your email address");
            context.setVariable("link", "http://localhost:8080/auth/activation?token="+token);
            //create an HTML template amd pass the variables to it
            String body = templateEngine.process("verification", context);
            //Send the verification email
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(user.getEmail());
            helper.setSubject("email address verification");
            helper.setText(body,true);
            javaMailSender.send(message);
        }

    }
}