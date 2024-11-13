package com.carshop.mycarshop.service.member;

import com.carshop.mycarshop.domain.member.EmailVerification;
import com.carshop.mycarshop.domain.member.EmailVerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmailVerificationService {

    private final EmailVerificationRepository emailVerificationRepository;

    public String generateVerification(String memberId) {
        if(!emailVerificationRepository.existsByMemberId(memberId)){
            EmailVerification verification = EmailVerification.builder()
                    .memberId(memberId)
                    .build();

            verification = emailVerificationRepository.save(verification);
            return verification.getVerificationid();
        }
        return getVerificationByMemberId(memberId);
    }

    public String getVerificationByMemberId(String memberId) {
        EmailVerification verification = emailVerificationRepository.findByMemberId(memberId);
        if (verification != null) {
            return verification.getVerificationid();
        }
        return null;
    }

    public String getVerificationByVerificationId(String verificationId) {{
        Optional<EmailVerification> result = emailVerificationRepository.findById(verificationId);
        if(result.isPresent()){
            return result.get().getMemberId();
        }
        return null;
    }}


}
