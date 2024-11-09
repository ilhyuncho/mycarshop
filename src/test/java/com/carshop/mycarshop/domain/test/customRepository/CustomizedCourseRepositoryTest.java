package com.carshop.mycarshop.domain.test.customRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    // 실제 db를 사용하기 위해
//@ActiveProfiles("test1")      // 굳이 필요 한가???
@TestPropertySource(locations = "classpath:application-test.properties")
class CustomizedCourseRepositoryTest {

    @Autowired
    private CustomizedCourseRepository customizedCourseRepository;

    @Test
//    @Commit // @DataJpaTest는 기본적으로 @Transactional 으로 설정 되어서 롤백 된다.
    public void test1(){
        Course course = Course.builder()
                .courseName("testCourse")
                .build();

        customizedCourseRepository.save(course);

        assertThat(Arrays.asList(customizedCourseRepository.findAll()).size()).isEqualTo(1);

    }
}