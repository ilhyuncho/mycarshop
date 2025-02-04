package com.carshop.mycarshop.dto.car;


import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CarSimpleResDTO {

    private String carNumber;
    private String carDetailModel;
}
