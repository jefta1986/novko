package com.novko.pdf;

import com.novko.internal.orders.Order;

import javax.mail.MessagingException;

public interface EmailService {

    void sendMessageWithAttachment(String to, String subject, String text, Order order)  throws MessagingException;
    void sendSimpleMessage(String to, String subject, String text)  throws MessagingException ;

}
