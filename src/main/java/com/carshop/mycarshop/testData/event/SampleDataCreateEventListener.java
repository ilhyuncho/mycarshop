package com.carshop.mycarshop.testData.event;


import com.carshop.mycarshop.common.util.Util;
import com.carshop.mycarshop.domain.car.Car;
import com.carshop.mycarshop.domain.car.CarRepository;
import com.carshop.mycarshop.domain.member.Member;
import com.carshop.mycarshop.domain.member.MemberRepository;
import com.carshop.mycarshop.domain.member.MemberRole;
import com.carshop.mycarshop.domain.reference.RefCarInfo;
import com.carshop.mycarshop.domain.reference.RefCarInfoRepository;
import com.carshop.mycarshop.domain.reference.RefCarSample;
import com.carshop.mycarshop.domain.reference.RefCarSampleRepository;
import com.carshop.mycarshop.domain.sellingCar.SellingCarStatus;
import com.carshop.mycarshop.domain.user.*;
import com.carshop.mycarshop.dto.sellingCar.SellingCarRegDTO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Component
@Log4j2
@Profile({"aws","test"})    // 이 클래스는 프로파일이 활성화될 때만 로드 된다.
@AllArgsConstructor
public class SampleDataCreateEventListener implements ApplicationListener<SampleMemberCreateEvent> {

    private final UserRepository userRepository;
    private final MemberRepository memberRepository;

    private final UserAlarmRepository userAlarmRepository;
    private final UserPointHistoryRepository userPointHistoryRepository;
    private final UserAddressBookRepository userAddressBookRepository;
    private final RefCarInfoRepository refCarInfoRepository;
    private final RefCarSampleRepository refCarSampleRepository;

    private final CarRepository carRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    @Transactional
    public void onApplicationEvent(SampleMemberCreateEvent event) {

        int createMemberCount = event.getCreateMemberCount();

        log.error("(SampleDataCreateEventListener) onApplicationEvent()!!!!!!!!");

        if(memberRepository.count() != createMemberCount || userRepository.count() != createMemberCount){

            memberRepository.deleteAll();
            userRepository.deleteAll();

            log.error("member, user데이터 생성!!!!!!!!!!!!!!");

            // member 생성
            IntStream.rangeClosed(1, createMemberCount).forEach(i -> {

                Member member = Member.builder()
                        .memberId("member" + i)
                        .memberPw(passwordEncoder.encode("1111"))
                        .email("email" + i + "@naver.com")
                        .verified(true)
                        .build();
                member.addRole(MemberRole.USER);

                if (i >= 8) {
                    member.addRole(MemberRole.ADMIN);
                }
                memberRepository.save(member);
            });

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
                    .toList();

            // User 생성
            IntStream.rangeClosed(1, createMemberCount).forEach(i -> {

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

                // 배송 주소록 추가
                UserAddressBook userAddressBook = UserAddressBook.builder()
                        .user(user)
                        .address(address)
                        .deliveryName("마이홈")
                        .RecipientName("김민수")
                        .deliveryRequest("문앞에 놓아주세요")
                        .RecipientPhoneNumber("01012349482")
                        .isMainAddress(true)
                        .isActive(true)
                        .build();
                userAddressBookRepository.save(userAddressBook);

                // 알림 추가
                UserAlarm userAlarm = UserAlarm.builder()
                        .user(user)
                        .alarmTitle("회원가입을 축하드립니다")
                        .alarmContent("앞으로 많이 이용해 주세요~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
                        .build();
                userAlarmRepository.save(userAlarm);

                if(i == 1){
                    Optional<RefCarSample> refCarSample = refCarSampleRepository.findById(1L);

                    if(refCarSample.isPresent()){
                        RefCarSample refCarSampleData = refCarSample.get();
                        Long refCarInfoId = refCarSampleData.getRefCarInfo().getRefCarInfoId();

                        Optional<RefCarInfo> refCarInfo = refCarInfoRepository.findById(refCarInfoId);

                        refCarInfo.ifPresent(info -> {

                            Car car = Car.builder()
                                    .carNumber(refCarSampleData.getCarNumber())
                                    .user(user)
                                    .refCarInfo(info)
                                    .carColors(refCarSampleData.getCarColor())
                                    .carYears(refCarSampleData.getCarYear())
                                    .carKm(refCarSampleData.getCarKm())
                                    .isActive(true)
                                    .build();

                            List<String> listImage = new ArrayList<>();
                            listImage.add("1111_carin.png");
                            listImage.add("2222_carin2.png");

                            car.resetImages(listImage, "carin.png");

                            // 차량 판매 등록
                            // SellType sellType = SellType.fromValue("auctionType");
                            SellingCarRegDTO sellingCarRegDTO = SellingCarRegDTO.builder()
                                    .sellingCarStatus(SellingCarStatus.PROCESSING)
                                    .sellType("auctionType")
                                    .requiredPrice(1000000)
                                    .build();
                            car.registerSellingCar(sellingCarRegDTO);

                            carRepository.save(car);
                        });
                    }
                }
                else if(i == 8){
                    Optional<RefCarSample> refCarSample = refCarSampleRepository.findById(2L);

                    if(refCarSample.isPresent()) {
                        RefCarSample refCarSampleData = refCarSample.get();
                        Long refCarInfoId = refCarSampleData.getRefCarInfo().getRefCarInfoId();

                        Optional<RefCarInfo> refCarInfo = refCarInfoRepository.findById(refCarInfoId);

                        refCarInfo.ifPresent(info -> {

                            Car car = Car.builder()
                                    .carNumber(refCarSampleData.getCarNumber())
                                    .user(user)
                                    .refCarInfo(info)
                                    .carColors(refCarSampleData.getCarColor())
                                    .carYears(refCarSampleData.getCarYear())
                                    .carKm(0)
                                    .isActive(true)
                                    .build();

                            List<String> listImage = new ArrayList<>();
                            listImage.add("3333_carin3.png");

                            car.resetImages(listImage, "carin3.png");

                            // 차량 판매 등록
                            // SellType sellType = SellType.fromValue("auctionType");
                            SellingCarRegDTO sellingCarRegDTO = SellingCarRegDTO.builder()
                                    .sellingCarStatus(SellingCarStatus.PROCESSING)
                                    .sellType("consultType")
                                    .requiredPrice(500_000)
                                    .build();
                            car.registerSellingCar(sellingCarRegDTO);

                            carRepository.save(car);

                        });

                    }
                }

            });

        }
    }
}