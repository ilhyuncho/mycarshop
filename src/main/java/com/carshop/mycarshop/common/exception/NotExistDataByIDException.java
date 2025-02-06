package com.carshop.mycarshop.common.exception;

public class NotExistDataByIDException extends RuntimeException{

    private static final long serialVersionUID = 4540534533L;

    public NotExistDataByIDException(String message){
        super(message);
    }
}