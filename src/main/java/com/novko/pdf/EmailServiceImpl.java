package com.novko.pdf;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailServiceImpl implements  EmailService{

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendMessageWithAttachment(String to, String subject, String text, DataSource attachment) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

//        FileSystemResource file = new FileSystemResource(new File(pathToAttachment));

        //ime fakture teba da bude u servisu jaspera
        helper.addAttachment("faktura.pdf", attachment);

        try {
            emailSender.send(message);
        }
        catch (MailException ex) {
            ex.getStackTrace();
        }
    }

    @Override
    public void sendSimpleMessage(String to, String subject, String text)  throws MessagingException {

    }
}
