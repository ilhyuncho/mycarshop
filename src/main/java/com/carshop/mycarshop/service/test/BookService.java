package com.carshop.mycarshop.service.test;

import com.carshop.mycarshop.common.exception.BookNotFoundException;
import com.carshop.mycarshop.domain.test.Book;
import com.carshop.mycarshop.domain.test.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book addBookData(Book book) {
        return bookRepository.save(book);
    }

    public Book getBookData(String BookNumber){

        Optional<Book> result = bookRepository.findByBookNumber(BookNumber);
        return result.orElseThrow(() -> new BookNotFoundException(BookNumber));
    }
}
