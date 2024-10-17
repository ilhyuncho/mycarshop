package com.carshop.mycarshop.common.exception;

public class BookAlreadyExistsException extends RuntimeException{
    public BookAlreadyExistsException(String bookNumber) {
        super("A book with bookNumber" + bookNumber + " already exists.");
    }
}
