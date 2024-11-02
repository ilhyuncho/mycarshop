package com.carshop.mycarshop.testData;

import com.carshop.mycarshop.domain.test.Book;
import com.carshop.mycarshop.domain.test.BookRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("test")    // 이 클래스는 testdata 프로파일이 활성화될 때만 로드 된다.
@Log4j2
public class BookDataLoader {
    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class) // 애플리케이션 시작 단계가 완료되면 발생한다.
    public void loadBookTestData(){

        log.error("loadBookTestData()!!!!!!!!");

        bookRepository.deleteAll();

        var book1 = Book.builder().bookNumber("1234567891").build();
        var book2 = Book.builder().bookNumber("1234567892").build();

        bookRepository.saveAll(List.of(book1, book2));
    }
}