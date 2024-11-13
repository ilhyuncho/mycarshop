package com.carshop.mycarshop.common.event.userRegister;

import com.carshop.mycarshop.config.ConfigProperties;
import com.carshop.mycarshop.domain.member.Member;
import com.carshop.mycarshop.service.member.EmailVerificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@Log4j2
@RequiredArgsConstructor
public class EmailVerificationListener implements ApplicationListener<UserRegistrationEvent> {

    // 고객에게 이메일을 발송
    
    private final JavaMailSender mailSender;

    private final EmailVerificationService verificationService;
    private final ConfigProperties configProperties;

    @Override
    public void onApplicationEvent(UserRegistrationEvent event) {

        log.error("EmailVerificationListener - onApplicationEvent() event : " + event.toString());

        Member member = event.getMember();
        String memberId = member.getMemberId();

        String verificationId = verificationService.generateVerification(memberId);
        String userEmail = event.getMember().getEmail();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("MyCarShop Account Verification");
        message.setText(getText(member, verificationId));
        message.setTo(userEmail);

        mailSender.send(message);
    }

    private String getText(Member member, String verificationId){

        String verifyDomainURL = configProperties.getVerifyDomainUrl();
        String encodedVerificationId = new String(Base64.getEncoder().encode(verificationId.getBytes()));

        StringBuffer buffer = new StringBuffer();
        buffer.append("Dear ").append(member.getMemberId()).append(" ")
                .append(System.lineSeparator()).append(System.lineSeparator());
        buffer.append("Your account has been successfully created");
        buffer.append("click the following link: ")
                .append("http://")
                .append(verifyDomainURL)
                .append(":8080/verify/email?id=")
                .append(encodedVerificationId);

        buffer.append(System.lineSeparator()).append(System.lineSeparator());

        return buffer.toString();
    }


}
