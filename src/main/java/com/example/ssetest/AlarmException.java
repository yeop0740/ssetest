package com.example.ssetest;

public class AlarmException extends RuntimeException{
    public AlarmException() {
        super();
    }

    public AlarmException(String message) {
        super(message);
    }

    public AlarmException(String message, Throwable cause) {
        super(message, cause);
    }
}
