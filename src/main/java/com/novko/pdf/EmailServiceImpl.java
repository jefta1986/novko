package com.novko.pdf;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.InputStream;

@Component
public class EmailServiceImpl implements  EmailService{

    private JavaMailSender emailSender;

    @Autowired
    public void setEmailSender(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }


//Emailmodel objekat sadrzi DataSource(byteove za generisanje attachmenta za pdf file), fileName(ime fajla koje generise u attachmentu)  , userLanguage
    @Override
    public void sendMessageWithAttachment(String to, String subject, String text, EmailModel model) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(new String[]{to, "novko49@gmail.com"}); //ili setTo i setCC   ,novko email
        helper.setSubject(subject);
        helper.setText(text);

        helper.addAttachment(model.getFileName(), model.getDataSource());  //setuje i ime fajla

        try {
            emailSender.send(message);
        }
        catch (MailException ex) {
            ex.getStackTrace();
        }
    }

    @Override
    public void sendSimpleMessage(String to, String subject, String text)  throws MessagingException {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to, "novko49@gmail.com");    //novko email

        msg.setSubject(subject);
        msg.setText(text);

        try {
            emailSender.send(msg);
        }
        catch (MailException ex) {
            ex.getStackTrace();
        }
    }
}
