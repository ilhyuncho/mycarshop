package com.carshop.mycarshop.dto.car;

import lombok.*;

//@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CarKmUpdateReqDTO {
    private Long carId;
    private int updateKmValue;
}