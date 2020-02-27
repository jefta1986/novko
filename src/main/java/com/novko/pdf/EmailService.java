package com.novko.pdf;

import javax.activation.DataSource;
import javax.mail.MessagingException;

public interface EmailService {

    void sendMessageWithAttachment(String to, String subject, String text, DataSource attachment)  throws MessagingException;
    void sendSimpleMessage(String to, String subject, String text)  throws MessagingException ;

}
