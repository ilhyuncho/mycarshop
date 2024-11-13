package com.carshop.mycarshop.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;


public interface EmailVerificationRepository extends JpaRepository<EmailVerification, String> {

    boolean existsByMemberId(String memberId);

    EmailVerification findByMemberId(String memberId);
}

