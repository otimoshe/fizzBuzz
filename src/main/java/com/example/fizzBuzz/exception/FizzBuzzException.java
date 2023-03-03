package com.example.fizzBuzz.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class FizzBuzzException extends RuntimeException {
    private HttpStatus status;

    public FizzBuzzException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
