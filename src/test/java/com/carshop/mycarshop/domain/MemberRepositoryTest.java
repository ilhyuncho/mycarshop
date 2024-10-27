package com.carshop.mycarshop.domain;

import com.carshop.mycarshop.domain.member.Member;
import com.carshop.mycarshop.domain.member.MemberRepository;
import com.carshop.mycarshop.domain.member.MemberRole;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
@ActiveProfiles("test")
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertMembers() {
        IntStream.rangeClosed(1, 10).forEach(i -> {

            Member member = Member.builder()
                    .memberId("member" + i)
                    .memberPw(passwordEncoder.encode("1111"))
                    .email("email" + i + "@naver.com")
                    .build();
            member.addRole(MemberRole.USER);

            if (i >= 90) {
                member.addRole(MemberRole.ADMIN);
            }
            memberRepository.save(member);

        });
    }

    @Test
    public void testRead() {
        Optional<Member> member100 = memberRepository.getWithRoles("member10");
        Member member = member100.orElseThrow();

        log.info(member);
        log.info(member.getRoleSet());

        member.getRoleSet().forEach(log::error);
    }
}