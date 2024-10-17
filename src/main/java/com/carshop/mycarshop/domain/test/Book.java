package com.carshop.mycarshop.domain.test;

import lombok.*;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="Books")
@Log4j2
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bookId")
    private Long bookId;

    @Column(name="bookNumber", length = 50, nullable = false)
    private String bookNumber;


}