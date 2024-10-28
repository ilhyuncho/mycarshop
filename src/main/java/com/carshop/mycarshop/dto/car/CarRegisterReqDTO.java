package com.carshop.mycarshop.dto.car;

import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CarRegisterReqDTO {
    private String carNumber;
}
