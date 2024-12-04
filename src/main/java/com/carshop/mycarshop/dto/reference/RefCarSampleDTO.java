package com.carshop.mycarshop.dto.reference;


import com.carshop.mycarshop.domain.car.CarSize;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class RefCarSampleDTO {
    private Long refCarInfoId;
    private String carNumber;
    private String carDetailModel;
    @Enumerated(EnumType.STRING)
    private CarSize carGrade;        // 등급 ( 소형, 중형, 대형.. )
    private String company;
    private String companyNation;

    private String carOption;       // 옵션
    private String carColor;
    private Integer carYear;      // 출시 연도
    private Integer carKm;          // 주행거리
    private LocalDate regDate;      // 최초 등록 날짜

}
