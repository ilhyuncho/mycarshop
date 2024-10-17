package com.carshop.mycarshop.controller.test;

import com.carshop.mycarshop.domain.car.Car;
import com.carshop.mycarshop.domain.test.Book;
import com.carshop.mycarshop.service.test.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book post(@RequestBody Book book) {

        return bookService.addBookData(book);
    }

}
