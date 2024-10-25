package com.carshop.mycarshop.domain.test;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByBookNumber(String bookNumber);

}
