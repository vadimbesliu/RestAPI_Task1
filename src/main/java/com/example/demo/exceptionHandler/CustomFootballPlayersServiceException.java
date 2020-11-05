package com.example.demo.exceptionHandler;

import org.springframework.http.HttpStatus;

public class CustomFootballPlayersServiceException extends Exception {
    private final HttpStatus responseCode;

    public CustomFootballPlayersServiceException(String message, HttpStatus code) {
        super(message);
        this.responseCode = code;
    }

    public HttpStatus getResponseCode() {
        return responseCode;
    }
}


