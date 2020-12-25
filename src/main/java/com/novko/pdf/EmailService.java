package com.novko.pdf;

import com.novko.internal.orders.Order;
import com.novko.security.User;

import javax.mail.MessagingException;

public interface EmailService {

    void sendMessageWithAttachment(Order order)  throws MessagingException;
    void sendUserRegistrationEmail(User user)  throws MessagingException ;
    void sendSimpleMessage(String to, String subject, String text)  throws MessagingException ;

}
