package com.mindata.hero.exception;

public class BaseException extends RuntimeException {
    public BaseException(ErrorDescription errorDescription) {
        super(errorDescription.getDescription());
    }
}
