package com.carshop.mycarshop.domain.car;

import com.carshop.mycarshop.config.JpaAuditingConfiguration;
import com.carshop.mycarshop.domain.reference.RefCarInfo;
import com.carshop.mycarshop.domain.reference.RefCarInfoRepository;
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
class RefCarInfoRepositoryTest {

    @Autowired
    private RefCarInfoRepository refCarInfoRepository;

    @Test
    @Commit // @DataJpaTest는 기본적으로 @Transactional 으로 설정 되어서 롤백 된다.
    public void test1(){

        RefCarInfo refCarInfo = RefCarInfo.builder()
                .build();

        refCarInfoRepository.saveAll(List.of(refCarInfo));


        assertThat(refCarInfoRepository.findAll()).size().isEqualTo(1);
    }
}