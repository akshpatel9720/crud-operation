package com.example.taskcrudoperation.serviceimpl;

import com.example.taskcrudoperation.Service.EmailService;
import com.example.taskcrudoperation.model.UserEntity;
import com.example.taskcrudoperation.util.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {
    public static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    Session session;

    private final TemplateEngine templateEngine;

    public EmailServiceImpl(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }


    @Override
    public void sendWelcomeMailToUser(UserEntity userEntity) {
        try {
            Context context = new Context();
            context.setVariable("user", userEntity);
            context.setVariable("hostname", ResponseMessage.URl);
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEntity.getEmail()));
//            String process = templateEngine.process("Registration/sendwelcomeemail.html", context);

            String process = ResponseMessage.URl + "/verifyAccount?email=" + userEntity.getEmail() + "&password=" + userEntity.getPassword();
            Multipart multipart = new MimeMultipart();
            BodyPart msg = new MimeBodyPart();
            msg.setDataHandler(new DataHandler(new ByteArrayDataSource(process, "text/html; charset=\"utf-8\"")));
            multipart.addBodyPart(msg);
            message.setSubject("Welcome to Frontendmeet", userEntity.getEmail());
            message.setContent(multipart);

            Transport.send(message);

            logger.info("Mail sent successfully to newly created user {} - verify ", userEntity.getEmail());
        } catch (Exception e) {
            logger.error("Problem occured while sending mail , Please check logs : " + e.getMessage());
        }
    }

    @Override
    public void sendPasswordResetMailToUser(UserEntity userEntity) {
        try {
            Context context = new Context();
            context.setVariable("user", userEntity);

            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEntity.getEmail()));
            message.setSubject("Reset password");
            String process=ResponseMessage.URl+"/register/resetPassword?oldPassword="+userEntity.getPassword() +"&email="+userEntity.getEmail();
            Multipart multipart = new MimeMultipart();
            BodyPart msg = new MimeBodyPart();
            msg.setDataHandler(new DataHandler(new ByteArrayDataSource(process, "text/html; charset=\"utf-8\"")));
            multipart.addBodyPart(msg);
            message.setContent(multipart);

            Transport.send(message);
            logger.info("Mail sent successfully to forgetPassword{} - Reset", userEntity.getEmail());
        } catch (Exception e) {
            logger.error("Problem occured while sending mail , Please check logs : " + e.getMessage());
        }
    }
}
