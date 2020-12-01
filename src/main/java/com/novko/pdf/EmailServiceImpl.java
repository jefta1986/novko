package com.novko.pdf;


import com.novko.internal.orders.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

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

@Component
public class EmailServiceImpl implements EmailService {

    private static final Logger LOG = Logger.getLogger(EmailServiceImpl.class.getName());


    private JavaMailSender emailSender;
    private GeneratePdf pdfService;

    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender, GeneratePdf pdfService) {
        this.emailSender = emailSender;
        this.pdfService = pdfService;
    }


    //Emailmodel objekat sadrzi DataSource(byteove za generisanje attachmenta za pdf file), fileName(ime fajla koje generise u attachmentu)  , userLanguage
    @Override
    public void sendMessageWithAttachment(String to, String subject, String text, Order order) throws MessagingException {
        String smtpHost = "smtp.gmail.com"; //replace this with a valid host
        int smtpPort = 587; //replace this with a valid port
        String sender = "novko49@gmail.com"; //replace this with a valid sender email address
        String password = "Nov@k1949";
        String recipient = to;

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
            textBodyPart.setText(text);

            //now write the PDF content to the output stream
            outputStream = new ByteArrayOutputStream();
            pdfService.createPdfFromOrder(outputStream, order);
            byte[] bytes = outputStream.toByteArray();

            //construct the pdf body part
            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
            MimeBodyPart pdfBodyPart = new MimeBodyPart();
            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
            pdfBodyPart.setFileName("Reciept_" + order.getUser().getId() + "_" + order.getId() + ".pdf");

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
            mimeMessage.setSubject(subject);
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


    //        MimeMessage message = emailSender.createMimeMessage();
//
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        helper.setTo(new String[]{to, "novko49@gmail.com"}); //ili setTo i setCC   ,novko email
//        helper.setSubject(subject);
//        helper.setText(text);
//
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        pdfService.createPdfFromOrder();
//
//        helper.addAttachment(model.getFileName(), model.getDataSource());  //setuje i ime fajla
//
//        try {
//            emailSender.send(message);
//        }
//        catch (MailException ex) {
//            ex.getStackTrace();
//        }
//}

    @Override
    public void sendSimpleMessage(String to, String subject, String text) throws MessagingException {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to, "novko49@gmail.com");    //novko email

        msg.setSubject(subject);
        msg.setText(text);

        try {
            emailSender.send(msg);
        } catch (MailException ex) {
            ex.getStackTrace();
        }
    }


//    public void email() {
//        String smtpHost = "yourhost.com"; //replace this with a valid host
//        int smtpPort = 587; //replace this with a valid port
//
//        String sender = "sender@yourhost.com"; //replace this with a valid sender email address
//        String recipient = "recipient@anotherhost.com"; //replace this with a valid recipient email address
//        String content = "dummy content"; //this will be the text of the email
//        String subject = "dummy subject"; //this will be the subject of the email
//
//        Properties properties = new Properties();
//        properties.put("mail.smtp.host", smtpHost);
//        properties.put("mail.smtp.port", smtpPort);
//        Session session = Session.getDefaultInstance(properties, null);
//
//        ByteArrayOutputStream outputStream = null;
//
//        try {
//            //construct the text body part
//            MimeBodyPart textBodyPart = new MimeBodyPart();
//            textBodyPart.setText(content);
//
//            //now write the PDF content to the output stream
//            outputStream = new ByteArrayOutputStream();
//            writePdf(outputStream);
//            byte[] bytes = outputStream.toByteArray();
//
//            //construct the pdf body part
//            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
//            MimeBodyPart pdfBodyPart = new MimeBodyPart();
//            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
//            pdfBodyPart.setFileName("test.pdf");
//
//            //construct the mime multi part
//            MimeMultipart mimeMultipart = new MimeMultipart();
//            mimeMultipart.addBodyPart(textBodyPart);
//            mimeMultipart.addBodyPart(pdfBodyPart);
//
//            //create the sender/recipient addresses
//            InternetAddress iaSender = new InternetAddress(sender);
//            InternetAddress iaRecipient = new InternetAddress(recipient);
//
//            //construct the mime message
//            MimeMessage mimeMessage = new MimeMessage(session);
//            mimeMessage.setSender(iaSender);
//            mimeMessage.setSubject(subject);
//            mimeMessage.setRecipient(Message.RecipientType.TO, iaRecipient);
//            mimeMessage.setContent(mimeMultipart);
//
//            //send off the email
//            Transport.send(mimeMessage);
//
//            System.out.println("sent from " + sender +
//                    ", to " + recipient +
//                    "; server = " + smtpHost + ", port = " + smtpPort);
//        } catch(Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            //clean off
//            if(null != outputStream) {
//                try { outputStream.close(); outputStream = null; }
//                catch(Exception ex) { }
//            }
//        }
//    }
}
