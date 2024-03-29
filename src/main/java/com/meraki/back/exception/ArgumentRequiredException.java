package com.meraki.back.exception;

public class ArgumentRequiredException extends Exception{

    private static final long serialVersionUIDLONG = 1L;

    public ArgumentRequiredException(String message) {
        super(message);
    }

}