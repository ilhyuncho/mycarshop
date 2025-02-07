package com.carshop.mycarshop.common.exception;

public class NotEnoughAddressBookException extends RuntimeException{

    private static final long serialVersionUID = 4540534535L;

    public NotEnoughAddressBookException(String message){
        super(message);
    }
}