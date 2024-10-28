package com.carshop.mycarshop.dto.statistics;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StatisticsReqDTO {
    private Long carId;
    private int selectYear;
    private String targetId;
}
