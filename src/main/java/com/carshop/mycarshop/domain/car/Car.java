package com.carshop.mycarshop.domain.car;


import lombok.*;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="Cars")
@Log4j2
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="carId")
    private Long carId;

    @Column(name="carNumber", length = 50, nullable = false)
    private String carNumber;


}
