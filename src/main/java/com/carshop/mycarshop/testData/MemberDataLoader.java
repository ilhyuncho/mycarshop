package com.carshop.mycarshop.testData;

import com.carshop.mycarshop.common.util.Util;
import com.carshop.mycarshop.domain.car.Car;
import com.carshop.mycarshop.domain.car.CarSize;
import com.carshop.mycarshop.domain.member.Member;
import com.carshop.mycarshop.domain.member.MemberRepository;
import com.carshop.mycarshop.domain.member.MemberRole;
import com.carshop.mycarshop.domain.sellingCar.SellingCarStatus;
import com.carshop.mycarshop.domain.test.Book;
import com.carshop.mycarshop.domain.test.BookRepository;
import com.carshop.mycarshop.domain.user.*;
import com.carshop.mycarshop.dto.sellingCar.SellingCarRegDTO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@Log4j2
@Profile({"aws","test"})    // 이 클래스는 프로파일이 활성화될 때만 로드 된다.
@AllArgsConstructor
public class MemberDataLoader {

    private final UserRepository userRepository;
    private final MemberRepository memberRepository;

    private final UserAlarmRepository userAlarmRepository;
    private final UserPointHistoryRepository userPointHistoryRepository;

    private final PasswordEncoder passwordEncoder;


    @EventListener(ApplicationReadyEvent.class) // 애플리케이션 시작 단계가 완료되면 발생한다.
    public void loadMemberTestData(){

        log.error("member 테이블 데이터 !!!!!!!!!!!!!!");
        log.error("member 테이블 데이터 !!!!!!!!!!!!!!");
        log.error("member 테이블 데이터 !!!!!!!!!!!!!!");
        log.error("member 테이블 데이터 !!!!!!!!!!!!!!");
        // member 생성
        if(memberRepository.count() == 0){
            log.error("member 테이블 데이터 생성!!!!!!!!!!!!!!");
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

        // 계정 생성
        if(userRepository.count() ==0){
            log.error("user 테이블 데이터 생성!!!!!!!!!!!!!!");
            // 참조 테이블 먼저 삭제
            //userPointHistoryRepository.deleteAll();

            // zip-code 생성  ( Stream 활용 테스트 겸 )
            IntStream randomStream = Util.createRandomStream(2, 100000, 999999);
            List<String> listZipcode = randomStream.mapToObj(String::valueOf).map(a -> {
                        StringBuilder buf = new StringBuilder(a);
                        buf.insert(3, "-");
                        return buf.toString();
                    })
                    .peek(log::error)
                    .collect(Collectors.toList());

            // User 생성
            IntStream.rangeClosed(1, 10).forEach(i -> {

                City city = new City(listZipcode.get(0), "부천시", "대한민국");
                Address address = Address.builder()
                        .city(city)
                        .street("수지로22번길55")
                        .detailAddress("101동 404호")
                        .build();

                City city1 = new City(listZipcode.get(1), "buchoen", "korea");
                Address address1 = Address.builder()
                        .city(city1)
                        .street("sugi22to257")
                        .detailAddress("101dong404ho")
                        .build();

                User user = User.builder()
                        .memberId("member" + i)
                        .userName("김민수" + i)
                        .address(address)
                        .billingAddress(address1)
                        .mPoint(0)
                        .mGrade(UserGradeType.GRADE_E)
                        .build();

                Long userId = userRepository.save(user).getUserId();

                // 알림 추가
                UserAlarm userAlarm = UserAlarm.builder()
                        .user(user)
                        .alarmTitle("회원가입을 축하드립니다")
                        .alarmContent("회원가입을 축하드립니다!!! 앞으로 많이 이용해 주세요~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
                        .build();
                userAlarmRepository.save(userAlarm);

            });
        }

    }
}