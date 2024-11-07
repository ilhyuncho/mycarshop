package com.carshop.mycarshop.common.exception.aws;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Getter
@ToString
@Log4j2
public class S3FileNullException extends RuntimeException{

    private static final long serialVersionUID = 4540534531L;
    private int code;       // 추가로 지정

    public S3FileNullException(String message){
        super(message);
        log.error("S3FileNullException call!!!!!!!!!!!!!");
    }
}