package com.carshop.mycarshop.common.exception.aws;

import com.carshop.mycarshop.domain.member.Member;

public enum AwsExceptions {
    FILE_CONVERT_FAIL("업로드할 파일 변환이 실패 하였습니다", 501);

    private final AwsTaskException awsTaskException;

    AwsExceptions(String msg, int code){
        awsTaskException = new AwsTaskException(msg, code);
    }

    public AwsTaskException get(){
        return awsTaskException;
    }

    public void get(Member member) {
    }
}