package com.nidaonder.library.core.exception;

import com.nidaonder.library.entities.BookBorrowing;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
