package com.carshop.mycarshop.common.exception;

public class InvalidUserPointException extends RuntimeException{

    private static final long serialVersionUID = 4540534532L;

    public InvalidUserPointException(String message){
        super(message);
    }
}