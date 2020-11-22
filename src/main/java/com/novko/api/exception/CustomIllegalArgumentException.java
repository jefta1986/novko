package com.novko.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class CustomIllegalArgumentException extends RuntimeException {

    private static final long serialVersionUID = -7405851232191580917L;

    public CustomIllegalArgumentException() {
        super();
    }

    public CustomIllegalArgumentException(String message) {
        super(message);
    }


}

