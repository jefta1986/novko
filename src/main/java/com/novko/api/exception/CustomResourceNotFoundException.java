package com.novko.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class CustomResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -5241047398760373562L;

    public CustomResourceNotFoundException() {
        super();
    }

    public CustomResourceNotFoundException(String message) {
        super(message);
    }


}

