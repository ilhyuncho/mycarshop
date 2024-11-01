package com.carshop.mycarshop.common.exception;

import java.io.Serial;

public class UserCreditNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 4540534532L;

    public UserCreditNotFoundException(String message){

        super(message);
    }
}
