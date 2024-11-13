package com.carshop.mycarshop.common.event.userRegister;

import com.carshop.mycarshop.domain.member.Member;
import org.springframework.context.ApplicationEvent;

import java.io.Serial;

// 새로운 고객이 등록되면 해당 이벤트가 발송 됨
// 이메일로 인증 코드 발송
public class UserRegistrationEvent extends ApplicationEvent {
    @Serial
    private static final long serialVersionUID = -24344534534666L;

    private final Member member;

    public UserRegistrationEvent(Member member) {
        super(member);
        this.member = member;
    }
    public Member getMember(){
        return member;
    }
}
