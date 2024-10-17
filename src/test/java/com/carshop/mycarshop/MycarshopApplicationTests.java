package com.carshop.mycarshop;

import com.carshop.mycarshop.domain.test.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT  // 임의의 포트를 듣는 서블릿 컨테이너 로드
)

@ActiveProfiles("integration")
class MycarshopApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void createTest(){
        var bookData = Book.builder()
                .bookNumber("0201fsd11f")
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
