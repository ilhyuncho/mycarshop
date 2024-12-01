package com.carshop.mycarshop.domain.car;

import com.carshop.mycarshop.config.JpaAuditingConfiguration;
import com.carshop.mycarshop.domain.reference.RefCarType;
import com.carshop.mycarshop.domain.reference.RefCarTypeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    // 실제 db를 사용하기 위해

@TestPropertySource(locations = "classpath:application-test.properties")
@Import(JpaAuditingConfiguration.class)
class RefCarTypeRepositoryTest {

    @Autowired
    private RefCarTypeRepository refCarTypeRepository;

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