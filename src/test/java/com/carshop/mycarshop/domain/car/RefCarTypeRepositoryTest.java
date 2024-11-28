package com.carshop.mycarshop.domain.car;

import com.carshop.mycarshop.config.JpaAuditingConfiguration;
import com.carshop.mycarshop.domain.test.customRepository.Course;
import com.carshop.mycarshop.domain.test.customRepository.CustomizedCourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    // 실제 db를 사용하기 위해

@TestPropertySource(locations = "classpath:application-test.properties")
@Import(JpaAuditingConfiguration.class)
class RefCarTypeRepositoryTest {

    @Autowired
    private RefCarTypeRepository refCarTypeRepository;

//    Map<String, List<String>> resultMap1 = new HashMap<>();
//        resultMap1.put("국산", List.of("현대", "기아", "kgm"));
//        resultMap1.put("수입", List.of("벤츠", "bmw", "테슬라"));
//
//    Map<String, List<String>> resultMap2 = new HashMap<>();
//        resultMap2.put("현대", List.of("쏘나타", "투싼", "아반떼"));
//        resultMap2.put("기아", List.of("k5", "스포티지", "k3"));
//        resultMap2.put("벤츠", List.of("c-class", "e-class", "s-class"));
//        resultMap2.put("bmw", List.of("5-series", "3-series", "x5"));
//
//    Map<String, List<String>> resultMap3 = new HashMap<>();
//        resultMap3.put("쏘나타", List.of("쏘나타 디 엣지(23년~현재)", "쏘나타 하이브리드(19년~23년)", "LF 쏘나타"));
//        resultMap3.put("투싼", List.of("더 뉴 투싼(23년~현재)", "올 뉴 투싼(15년~20년)", "투싼(04년~09년)"));
//        resultMap3.put("아반떼", List.of("더 뉴 아반떼(23년~현재)", "아반떼 AD(15년~18년)", "더 뉴 아반떼(13년~15년)"));
//
    @Test
    @Commit // @DataJpaTest는 기본적으로 @Transactional 으로 설정 되어서 롤백 된다.
    public void test1(){

        RefCarType refCarType = RefCarType.builder()
                                    .typeId(1)
                                    .groupIndex(1)
                                    .typeName("국산")
                                    .parentTypeId(0)
                                     .order(1)
                                    .build();

        RefCarType refCarType1 = RefCarType.builder()
                                    .typeId(2)
                                    .groupIndex(1)
                                    .typeName("수입")
                                    .parentTypeId(0)
                                    .order(2)
                                    .build();

        RefCarType refCarType2 = RefCarType.builder()
                .typeId(3)
                .groupIndex(2)
                .typeName("현대")
                .parentTypeId(1)
                .order(1)
                .build();
        RefCarType refCarType3 = RefCarType.builder()
                .typeId(4)
                .groupIndex(2)
                .typeName("기아")
                .parentTypeId(1)
                .order(2)
                .build();
        RefCarType refCarType4 = RefCarType.builder()
                .typeId(5)
                .groupIndex(2)
                .typeName("kgm")
                .parentTypeId(1)
                .order(3)
                .build();

        RefCarType refCarType5 = RefCarType.builder()
                .typeId(6)
                .groupIndex(2)
                .typeName("벤츠")
                .parentTypeId(2)
                .order(1)
                .build();
        RefCarType refCarType6 = RefCarType.builder()
                .typeId(7)
                .groupIndex(2)
                .typeName("bmw")
                .parentTypeId(2)
                .order(2)
                .build();
        RefCarType refCarType7 = RefCarType.builder()
                .typeId(8)
                .groupIndex(2)
                .typeName("테슬라")
                .parentTypeId(2)
                .order(3)
                .build();

        refCarTypeRepository.saveAll(List.of(refCarType, refCarType1, refCarType2,
                refCarType3, refCarType4, refCarType5, refCarType6, refCarType7));


        assertThat(refCarTypeRepository.findAll()).size().isEqualTo(8);

    }
}