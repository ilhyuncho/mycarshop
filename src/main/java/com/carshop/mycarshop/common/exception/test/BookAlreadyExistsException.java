package com.carshop.mycarshop.common.exception.test;

public class BookAlreadyExistsException extends RuntimeException{
    public BookAlreadyExistsException(String bookNumber) {
        super("A book with bookNumber" + bookNumber + " already exists.");
    }
}
