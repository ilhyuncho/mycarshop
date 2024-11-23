package com.carshop.mycarshop.dto.user;


import com.carshop.mycarshop.domain.user.PointType;
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
    private PointType pointType;
    private Integer pointValue;
    private LocalDate regDate;

}
