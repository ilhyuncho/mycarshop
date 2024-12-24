package com.carshop.mycarshop;

import com.carshop.mycarshop.domain.test.Book;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT  // 임의의 포트를 듣는 서블릿 컨테이너 로드
)
@ActiveProfiles("test") // test/resources/application-test.properties 설정 파일을 이용하게 된다
@Log4j2
class MycarshopApplicationTests {

    @Autowired
    private WebTestClient webTestClient;        // 리액티브 웹 의존성

    @Test
    @Transactional  // @Transactional을 지정 해도  rollback 되지 않는다

            // @SpringBootTest를 RANDOM_PORT나 DEFINED_PORT로 사용하면 별도의 쓰레드에서 스프링 컨테이너가 실행된다.
            // 테스트가 끝나고 이를 롤백시키려면 하나의 트랜잭션으로 묶여야 하는데,
            // 스프링 컨테이너가 실제로 구동되어 테스트와 다른 쓰레드에서 실행되니 하나의 트랜잭션으로 묶일 수 없는 것이다.
            // 그래서 @SpringBootTest를 RANDOM_PORT나 DEFINED_PORT로 사용하면
            // @Transactional을 사용해도 롤백되지 않는다.
            //        출처: https://mangkyu.tistory.com/264 [MangKyu's Diary:티스토리]
    @Rollback
    void createTest(){
        var bookData = Book.builder()
                .bookNumber("0201f133sd14f")
                .build();

        webTestClient.post()
                .uri("/books")
                .bodyValue(bookData)
                .exchange()     // 요청을 전송
                .expectStatus().isCreated()
                .expectBody(Book.class).value(actualBook -> {
                    assertThat(actualBook).isNotNull();
                    assertThat(actualBook.getBookNumber()).isEqualTo(bookData.getBookNumber());
                });
    }

    @Test
    void contextLoads() {

    }

}
