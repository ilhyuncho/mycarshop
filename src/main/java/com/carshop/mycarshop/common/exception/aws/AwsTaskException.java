package com.carshop.mycarshop.common.exception.aws;

import lombok.Getter;

@Getter
public class AwsTaskException extends RuntimeException{
    private final String msg;
    private final int code;

    public AwsTaskException(String msg, int code){
        this.msg = msg;
        this.code = code;
    }
}