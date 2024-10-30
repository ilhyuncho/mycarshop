package com.carshop.mycarshop.testData;

import com.carshop.mycarshop.domain.reference.*;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Log4j2
@Profile({"test","aws"})    // 이 클래스는 프로파일이 활성화될 때만 로드 된다.
@AllArgsConstructor
public class RefDataLoader {

    private final RefCarConsumableRepository refCarConsumableRepository;
    private final RefPointSituationRepository refPointSituationRepository;

    @EventListener(ApplicationReadyEvent.class) // 애플리케이션 시작 단계가 완료되면 발생한다.
    public void loadRefSampleData() {

        /////////////////////////////////
        // RefPointSituation 생성
        long count = refPointSituationRepository.count();
        if(count == 0){

            List<RefPointSituation> listRefPointSituation = new ArrayList<>();
            RefPointSituation refPointSituation = RefPointSituation.builder()
                    .situationName("첫로그인")
                    .situationDesc("첫로그인 설명")
                    .AccumCycle(AccumCycle.FIRST_TIME)
                    .gainPoint(1000)
                    .viewOrder(1)
                    .build();
            listRefPointSituation.add(refPointSituation);

            RefPointSituation refPointSituation1 = RefPointSituation.builder()
                    .situationName("매일 로그인")
                    .situationDesc("매일 로그인 설명")
                    .AccumCycle(AccumCycle.EVERYDAY)
                    .gainPoint(50)
                    .viewOrder(2)
                    .build();
            listRefPointSituation.add(refPointSituation1);

            RefPointSituation RefPointSituation2 = RefPointSituation.builder()
                    .situationName("내차 등록")
                    .situationDesc("내차 등록 설명")
                    .AccumCycle(AccumCycle.EACH_ITEM)
                    .gainPoint(500)
                    .viewOrder(3)
                    .build();
            listRefPointSituation.add(RefPointSituation2);

            RefPointSituation refPointSituation3 = RefPointSituation.builder()
                    .situationName("차 판매 등록")
                    .situationDesc("차 판매 등록 설명")
                    .AccumCycle(AccumCycle.EACH_ITEM)
                    .gainPoint(400)
                    .viewOrder(4)
                    .build();
            listRefPointSituation.add(refPointSituation3);

            RefPointSituation refPointSituation4 = RefPointSituation.builder()
                    .situationName("차 판매 등록")
                    .situationDesc("차 판매 등록 설명")
                    .AccumCycle(AccumCycle.EACH_ITEM)
                    .gainPoint(400)
                    .viewOrder(5)
                    .build();
            listRefPointSituation.add(refPointSituation4);

            refPointSituationRepository.saveAll(listRefPointSituation);
        }

        /////////////////////////////////
        // refCarConsumable 생성
        long count1 = refCarConsumableRepository.count();
        if(count1 == 0){

            RefCarConsumable refCarConsumable1 = RefCarConsumable.builder()
                    .name("주유")
                    .repairType("상시")
                    .replaceCycleKm(0)
                    .replaceCycleMonth(0)
                    .viewOrder(0)
                    .build();

            refCarConsumableRepository.save(refCarConsumable1);

            List<String> listName = new ArrayList<>();
            listName.add("엔진오일 및 오일 필터");
            listName.add("에어컨 필터");
            listName.add("브레이크 오일");
            listName.add("에어클리너 필터");
            listName.add("배터리");
            listName.add("점화플러그");
            listName.add("구동 벨트");
            listName.add("파워스티어링 오일");
            listName.add("브레이크 패드 및 디스크");
            listName.add("미션 오일");

            AtomicInteger index = new AtomicInteger();
            listName.forEach(name -> {
                int random = (int) (Math.random() * 50);

                RefCarConsumable refCarConsumable = RefCarConsumable.builder()
                        .name(name)
                        .repairType( random % 2 == 0 ? "교체" : "점검" )
                        .replaceCycleKm(5000 + (100 * random))
                        .replaceCycleMonth(12 + random)
                        .viewOrder(index.getAndIncrement())
                        .build();

                refCarConsumableRepository.save(refCarConsumable);
            });
        }
    }
}
