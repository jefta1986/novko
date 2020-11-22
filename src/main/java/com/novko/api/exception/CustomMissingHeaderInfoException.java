package com.novko.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomMissingHeaderInfoException extends RuntimeException {

    private static final long serialVersionUID = -3456301990403489368L;

    public CustomMissingHeaderInfoException() {
        super();
    }

    public CustomMissingHeaderInfoException(String message) {
        super(message);
    }
}

