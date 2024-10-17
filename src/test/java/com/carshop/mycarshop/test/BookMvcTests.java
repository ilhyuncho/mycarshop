package com.carshop.mycarshop.test;

import com.carshop.mycarshop.common.exception.BookNotFoundException;
import com.carshop.mycarshop.controller.test.BookController;
import com.carshop.mycarshop.service.test.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)       // 스프링 MVC 컴포넌트에 중점을 둠
public class BookMvcTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean       // 스프링 애플리케이션 컨텍스트에 BookService의 모의 객체를 추가
    private BookService bookService;

    @Test
    void returnError() throws Exception{
        String bookNumber = "23232df";
        given(bookService.getBookData(bookNumber))          // 모의 빈이 어떻게 작동할 것인지 규정
                .willThrow(BookNotFoundException.class);

        mockMvc.perform(get("/books/" + bookNumber))
                .andExpect(status().isNotFound());      // 응답이 "404발견되지 않음" 상태를 가질 것으로 예상


    }
}

















