package com.novko.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.OK)
public class MailSendingException extends RuntimeException {

    private static final long serialVersionUID = -7405851232191580917L;

    public MailSendingException() {
        super();
    }

    public MailSendingException(String message) {
        super(message);
    }


}

