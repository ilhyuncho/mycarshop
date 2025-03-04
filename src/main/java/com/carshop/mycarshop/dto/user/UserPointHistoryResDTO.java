package com.carshop.mycarshop.dto.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPointHistoryResDTO {

    private String situationName;
    private Integer pointValue;
    private LocalDate regDate;

}
