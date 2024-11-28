package com.carshop.mycarshop.testData;

import com.carshop.mycarshop.domain.car.RefCarType;
import com.carshop.mycarshop.domain.car.RefCarTypeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log4j2
@Profile({"aws","test"})    // 이 클래스는 프로파일이 활성화될 때만 로드 된다.
@AllArgsConstructor
public class RefCarTypeSampleDataLoader {

    private final RefCarTypeRepository refCarTypeRepository;



    @EventListener(ApplicationReadyEvent.class) // 애플리케이션 시작 단계가 완료되면 발생한다.
    public void loadRefCarTypeSampleData() {

        RefCarTypeInfoBuilder refCarTypeInfoBuilder = new RefCarTypeInfoBuilder();

        List<RefCarType> listRefCarSample = refCarTypeRepository.findAll();

        if(refCarTypeInfoBuilder.listRefCarType.size() != listRefCarSample.size()){

            log.error("RefCarTypeSampleData saveAll()~~~~~~~~~~~~~");
            refCarTypeRepository.deleteAll();

            refCarTypeRepository.saveAll(refCarTypeInfoBuilder.listRefCarType);
        }
    }
}
