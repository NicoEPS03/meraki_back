package com.meraki.back.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ArgumentRequiredException extends Exception {

    private static final long serialVersionUIDLONG = 1L;

    public ArgumentRequiredException(String message) {
        super(message);
    }

}