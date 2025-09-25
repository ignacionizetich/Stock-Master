package org.example.backend.Exceptions;

public class UserException extends RuntimeException {
    public UserException(String message) {
        super("USER COULD NOT BE CREATED: " + message);
    }
}
