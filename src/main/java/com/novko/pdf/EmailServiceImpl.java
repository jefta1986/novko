package com.novko.pdf;


import com.novko.common.ApplicationConstants;
import com.novko.internal.orders.Order;
import com.novko.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.util.Properties;
import java.util.logging.Logger;

@Service("emailService")
public class EmailServiceImpl implements EmailService {

    private static final Logger LOG = Logger.getLogger(EmailServiceImpl.class.getName());


    private JavaMailSender emailSender;
    private GeneratePdf pdfService;

    @Autowired
    public void setEmailSender(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Autowired
    public void setPdfService(GeneratePdf pdfService) {
        this.pdfService = pdfService;
    }


    //Emailmodel objekat sadrzi DataSource(byteove za generisanje attachmenta za pdf file), fileName(ime fajla koje generise u attachmentu)  , userLanguage
    @Override
    @Async
    public void sendMessageWithAttachment(Order order) throws MessagingException {
        User user = order.getUser();

        String smtpHost = "smtp.gmail.com"; //replace this with a valid host
        int smtpPort = 587; //replace this with a valid port
        String sender = "novko49@gmail.com"; //replace this with a valid sender email address
        String password = "Nov@k1949";
        String recipient = user.getUsername(); //username je recipient

        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.debug", true);

//        Session session = Session.getDefaultInstance(properties, null);
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, password);
            }
        });

        ByteArrayOutputStream outputStream = null;

        try {
            //construct the text body part
            MimeBodyPart textBodyPart = new MimeBodyPart();

            if (user.getLanguage().equals("SR")) {
                textBodyPart.setText(String.format(ApplicationConstants.ORDER_SERBIAN_EMAIL, user.getUsername())); //body text za email SRPSKI
            } else if (user.getLanguage().equals("EN")) {
                textBodyPart.setText(String.format(ApplicationConstants.ORDER_ENGLISH_EMAIL, user.getUsername())); //body text za email ENGLESKI
            }

            //now write the PDF content to the output stream
            outputStream = new ByteArrayOutputStream();
            pdfService.createPdfFromOrder(outputStream, order);
            byte[] bytes = outputStream.toByteArray();

            //construct the pdf body part
            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
            MimeBodyPart pdfBodyPart = new MimeBodyPart();
            pdfBodyPart.setDataHandler(new DataHandler(dataSource));

            if (user.getLanguage().equals("SR")) {
                pdfBodyPart.setFileName("Faktura_" + user.getId() + "_" + order.getId() + ".pdf"); //proveri za ime PDF fajla!!
            } else if (user.getLanguage().equals("EN")) {
                pdfBodyPart.setFileName("Receipt_" + user.getId() + "_" + order.getId() + ".pdf"); //proveri za ime PDF fajla!!
            }

            //construct the mime multi part
            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(textBodyPart);
            mimeMultipart.addBodyPart(pdfBodyPart);

            //create the sender/recipient addresses
            InternetAddress iaSender = new InternetAddress(sender);
            InternetAddress iaRecipient = new InternetAddress(recipient);

            //construct the mime message
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setSender(iaSender);

            if (user.getLanguage().equals("SR")) {
                mimeMessage.setSubject(ApplicationConstants.SUBJECT_ORDER_SERBIAN_EMAIL);
            } else if (user.getLanguage().equals("EN")) {
                mimeMessage.setSubject(ApplicationConstants.SUBJECT_ORDER_ENGLISH_EMAIL);
            }

            mimeMessage.setRecipient(Message.RecipientType.TO, iaRecipient);
            mimeMessage.setContent(mimeMultipart);

            //send off the email
            Transport.send(mimeMessage);
//            emailSender.send(mimeMessage);

            LOG.info("sent from " + sender +
                    ", to " + recipient +
                    "; server = " + smtpHost + ", port = " + smtpPort);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //clean off
            if (null != outputStream) {
                try {
                    outputStream.close();
                    outputStream = null;
                } catch (Exception ex) {
                }
            }
        }
    }

    @Override
    @Async
    public void sendUserRegistrationEmail(User user) throws MessagingException {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(user.getUsername(), "novko49@gmail.com");    //salje useru(email je username) i salje Novku (adminu) email

        if (user.getLanguage().equals("SR")) {
            msg.setSubject(ApplicationConstants.SUBJECT_USER_REGISTRATION_SERBIAN_EMAIL);
            msg.setText(ApplicationConstants.USER_REGISTRATION_SERBIAN_EMAIL);
        } else if (user.getLanguage().equals("EN")) {
            msg.setSubject(ApplicationConstants.SUBJECT_USER_REGISTRATION_ENGLISH_EMAIL);
            msg.setText(ApplicationConstants.USER_REGISTRATION_ENGLISH_EMAIL);
        }

        try {
            emailSender.send(msg);
        } catch (MailException ex) {
            ex.getStackTrace();
        }
    }

    @Override
    @Async
    public void sendSimpleMessage(String to, String subject, String text) throws MessagingException {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to, "novko49@gmail.com");    //salje ka useru (username=email) i Novku (adminu) email

        msg.setSubject(subject);
        msg.setText(text);

        try {
            emailSender.send(msg);
        } catch (MailException ex) {
            ex.getStackTrace();
        }
    }

}
