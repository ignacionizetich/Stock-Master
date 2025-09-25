package org.example.backend.Exceptions;

public class ItemException extends RuntimeException {
    public ItemException(String message) {
        super("ITEM COULD NOT BE CREATED: " + message);
    }
}
