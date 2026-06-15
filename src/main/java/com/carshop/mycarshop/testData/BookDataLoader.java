package com.carshop.mycarshop.testData;

import com.carshop.mycarshop.domain.test.Book;
import com.carshop.mycarshop.domain.test.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Log4j2
@Profile({"test", "aws"})
@RequiredArgsConstructor
public class BookDataLoader {

    private final BookRepository bookRepository;
    private final Environment environment;

    @Async
    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        if (environment.acceptsProfiles(Profiles.of("aws"))) {
            if (bookRepository.count() > 0) {
                return;
            }
            saveSampleBooks();
            log.info("Books 샘플 데이터 생성 (aws)");
            return;
        }

        bookRepository.deleteAll();
        saveSampleBooks();
        log.info("Books 샘플 데이터 재생성 (test)");
    }

    private void saveSampleBooks() {
        var book1 = Book.builder().bookNumber("1234567891").build();
        var book2 = Book.builder().bookNumber("1234567894").build();
        bookRepository.saveAll(List.of(book1, book2));
    }
}
