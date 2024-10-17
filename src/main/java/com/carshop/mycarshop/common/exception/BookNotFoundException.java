package com.carshop.mycarshop.common.exception;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String bookNumber) {
        super("The book with bookNumber" + bookNumber + " was not found.");
    }
}
