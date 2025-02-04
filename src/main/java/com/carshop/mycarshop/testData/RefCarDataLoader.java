package com.carshop.mycarshop.testData;

import com.carshop.mycarshop.common.util.Util;
import com.carshop.mycarshop.domain.buyingCar.BuyingCarRepository;
import com.carshop.mycarshop.domain.car.CarRepository;
import com.carshop.mycarshop.domain.reference.*;
import com.carshop.mycarshop.domain.sellingCar.SellingCarRepository;
import com.carshop.mycarshop.testData.builder.RefCarDataBuilder;
import com.carshop.mycarshop.testData.builder.RefCarInfoBuilder;
import com.carshop.mycarshop.testData.event.SampleMemberCreateEvent;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@Log4j2
@Profile({"aws","test","testRepli"})    // 이 클래스는 프로파일이 활성화될 때만 로드 된다.
@AllArgsConstructor
@Transactional
public class RefCarDataLoader {

    private final RefCarInfoRepository refCarInfoRepository;
    private final RefCarGradeRepository refCarGradeRepository;
    private final RefCarSampleRepository refCarSampleRepository;
    private final SellingCarRepository sellingCarRepository;
    private final BuyingCarRepository buyingCarRepository;
    private final CarRepository carRepository;

    private final ApplicationEventPublisher eventPublisher;

    static String[] hangul = {"가", "강", "건", "경", "고", "관", "광", "구", "규", "근", "기", "길", "나", "남", "노", "누", "다",
            "단", "달", "담", "대", "덕", "도", "동", "두", "라", "래", "로", "루", "리", "마", "만", "명", "무", "문", "미", "민", "바", "박",
            "백", "범", "별", "병", "보", "빛", "사", "산", "상", "새", "서", "석", "선", "설", "섭", "성", "세", "소", "솔", "수", "숙", "순",
            "숭", "슬", "승", "시", "신", "아", "안", "애", "엄", "여", "연", "영", "예", "오", "옥", "완", "요", "용", "우", "원", "월", "위",
            "유", "윤", "율", "으", "은", "의", "이", "익", "인", "일", "잎", "자", "잔", "장", "재", "전", "정", "제", "조", "종", "주", "준",
            "중", "지", "진", "찬", "창", "채", "천", "철", "초", "춘", "충", "치", "탐", "태", "택", "판", "하", "한", "해", "혁", "현", "형",
            "혜", "호", "홍", "화", "환", "회", "효", "훈", "휘", "희", "운", "모", "배", "부", "림", "봉", "혼", "황", "량", "린", "을", "비",
            "솜", "공", "면", "탁", "온", "디", "항", "후", "려", "균", "묵", "송", "욱", "휴", "언", "령", "섬", "들", "견", "추", "걸", "삼",
            "열", "웅", "분", "변", "양", "출", "타", "흥", "겸", "곤", "번", "식", "란", "더", "손", "술", "훔", "반", "빈", "실", "직", "흠",
            "흔", "악", "람", "뜸", "권", "복", "심", "헌", "엽", "학", "개", "롱", "평", "늘", "늬", "랑", "얀", "향"};

    private void initCarInfoData(){
        buyingCarRepository.deleteAll();
        sellingCarRepository.deleteAll();
        carRepository.deleteAll();
        refCarSampleRepository.deleteAll();
        refCarInfoRepository.deleteAll();
    }
    @EventListener(ApplicationReadyEvent.class) // 애플리케이션 시작 단계가 완료되면 발생한다.
    @Transactional(readOnly = false)
    public void loadRefCarData() {

        log.error("(ApplicationReadyEvent) loadRefCarData()!!!!!!!!");

        RefCarInfoBuilder refCarInfoBuilder = new RefCarInfoBuilder();
        RefCarDataBuilder refCarDataBuilder = new RefCarDataBuilder();
        //RefCarOptionBuilder refCarOptionBuilder = new RefCarOptionBuilder();

        List<RefCarInfo> listRefCarInfo = refCarInfoRepository.findAll();

        if(refCarInfoBuilder.listRefCarInfo.size() != listRefCarInfo.size()
            || listRefCarInfo.size() == 0){

            log.error("1. RefCarInfo saveAll()~~~~~~~~~~~~~");

            initCarInfoData();

            refCarGradeRepository.saveAll(refCarDataBuilder.listRefGrade);
            refCarInfoRepository.saveAll(refCarInfoBuilder.listRefCarInfo);

            // 차량 별 옵션 셋팅
            List<RefCarInfo> savedRefCarInfo = refCarInfoRepository.findAll();
            List<RefCarGrade> savedRefCarGrade = refCarGradeRepository.findAll();

            savedRefCarInfo.forEach(refCarInfo -> {

                savedRefCarGrade.stream()
                        .filter(refCarGrade -> {
                            return refCarGrade.getCarDetailModel().getName().equals(refCarInfo.getCarDetailModel());})
                        .forEach(refCarInfo::addRefCarGrade);

            });

        }

        // 차량 샘플 데이터 100개 생성
        loadRefCarSampleData();

        // 고객 샘플 데이터 n개 생성
        SampleMemberCreateEvent sampleMemberCreateEvent = new SampleMemberCreateEvent(this, "create member", 10);
        eventPublisher.publishEvent(sampleMemberCreateEvent);

    }

    public void loadRefCarSampleData() {

        List<RefCarInfo> listRefCarInfo = refCarInfoRepository.findAll();

        List<RefCarSample> listRefCarSample = refCarSampleRepository.findAll();

        if(listRefCarSample.size() != 100){
            refCarSampleRepository.deleteAll();

            log.error("loadRefCarSampleData start~~~~~~~~~~~~~~~~~~");
            // 차 색상 생성
            Map<Integer, String> mapColor = Map.of(0,"흰색",
                    1, "빨강색",
                    2,"검은색",
                    3,"은색",
                    4,"파란색",
                    5, "회색");

            // 차 정보 생성
            //RefCarInfoBuilder carBuilder = new RefCarInfoBuilder();
            // RefCarInfoBuilder carBuilder = new RefCarInfoBuilder();

            // 임의로 차 넘버 생성  ( Stream 활용 테스트 겸 )
            IntStream randomStream = Util.createRandomStream(100, 100_0000, 999_9999);
            List<String> listCarNumber = randomStream.mapToObj(String::valueOf).map(a -> {
                        StringBuilder buf = new StringBuilder(a);

                        int skipIndex = new Random().nextInt(hangul.length - 1);
                        String temp = Arrays.stream(hangul).skip(skipIndex).findFirst().get();

                        buf.insert(3, temp);
                        return buf.toString();
                    })
                    //  .peek(log::error)
                    .toList();

            IntStream.rangeClosed(1,100).forEach(a -> {

                int randomYear = new Random().nextInt(30) + 1990;
                int randomMonth = new Random().nextInt(11) + 1;
                int randomDate = new Random().nextInt(28) + 1;
                int randomKm = new Random().nextInt(200000) + 1000;
                int randomColor= new Random().nextInt(mapColor.size());

                if(listRefCarInfo.size() > 0 ){
                    int carInfoIndex = new Random().nextInt(listRefCarInfo.size());

                    // RefCarInfo 정보 get
                    RefCarInfo refCarInfo = listRefCarInfo.get(carInfoIndex);
                    int randomCarYear = new Random().nextInt(refCarInfo.getCarYearStart(), refCarInfo.getCarYearEnd()+1);

                    // 여기서~~~~~~~
                    if(refCarInfo.getRefCarGradeSet().size() > 0){
                        RefCarGrade refCarGrade = refCarInfo.getRefCarGradeSet().stream()
                                .skip(new Random().nextInt(refCarInfo.getRefCarGradeSet().size()))
                                .findFirst().get();

                        if(refCarGrade.getCarTrimSet().size() > 0) {
                            RefCarTrim refCarTrim = refCarGrade.getCarTrimSet().stream()
                                    .skip(new Random().nextInt(refCarGrade.getCarTrimSet().size()))
                                    .findFirst().get();

                            RefCarSample refCarSample = RefCarSample.builder()
                                    .carNumber(listCarNumber.get(a - 1))
                                    .refCarInfo(refCarInfo)
                                    .carYear(randomCarYear)
                                    .carKm(randomKm)
                                    .carColor(mapColor.get(randomColor))

                                    .refCarGrade(refCarGrade)
                                    .refCarTrim(refCarTrim)
                                    .carFuelType(refCarGrade.getCarFuelType())

                                    .regDate(LocalDate.of(randomYear, randomMonth, randomDate))
                                    .build();
                            log.error("refCarSampleRepository.save~~~~~");
                            refCarSampleRepository.save(refCarSample);
                        }

                    }
                    //else{
                      //  log.error("refCarInfo refCarGrade is null!!! " + refCarInfo.getRefCarInfoId() + " , "+ refCarInfo.getCarDetailModel());
                   // }
                }
            });

            log.error("2. refCarSampleRepository.save success!!!!!!!!!!!!!!");
        }
    }
}
