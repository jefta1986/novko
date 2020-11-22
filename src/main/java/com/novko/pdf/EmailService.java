package com.novko.pdf;

import javax.mail.MessagingException;

public interface EmailService {

    void sendMessageWithAttachment(String to, String subject, String text, EmailModel model)  throws MessagingException;
    void sendSimpleMessage(String to, String subject, String text)  throws MessagingException ;

}
