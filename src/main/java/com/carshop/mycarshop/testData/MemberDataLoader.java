package com.carshop.mycarshop.testData;

import com.carshop.mycarshop.domain.member.Member;
import com.carshop.mycarshop.domain.member.MemberRepository;
import com.carshop.mycarshop.domain.member.MemberRole;
import com.carshop.mycarshop.domain.test.Book;
import com.carshop.mycarshop.domain.test.BookRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
@Log4j2
@Profile("aws")    // 이 클래스는 testdata 프로파일이 활성화될 때만 로드 된다.
public class MemberDataLoader {
    private final MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public MemberDataLoader(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @EventListener(ApplicationReadyEvent.class) // 애플리케이션 시작 단계가 완료되면 발생한다.
    public void loadMemberTestData(){

        log.error("loadMemberTestData()!!!!!!!!!!!!!!");

        memberRepository.deleteAll();

        // member 생성
        IntStream.rangeClosed(1, 10).forEach(i -> {

            Member member = Member.builder()
                    .memberId("member" + i)
                    .memberPw(passwordEncoder.encode("1111"))
                    .email("email" + i + "@naver.com")
                    .build();
            member.addRole(MemberRole.USER);

            if (i >= 8) {
                member.addRole(MemberRole.ADMIN);
            }
            memberRepository.save(member);

        });
    }
}