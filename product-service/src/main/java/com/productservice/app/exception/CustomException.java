package com.productservice.app.exception;

public class CustomException extends  RuntimeException{
    public CustomException(String message, Integer id) {
        super(String.format(message, id));
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
