package com.carshop.mycarshop.dto.reference;


import com.carshop.mycarshop.domain.car.CarSize;
import com.carshop.mycarshop.domain.reference.RefCarGrade;
import com.carshop.mycarshop.domain.reference.RefCarTrim;
import com.carshop.mycarshop.domain.reference.carType.CarFuelType;
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
    private CarSize carSize;        // 등급 ( 소형, 중형, 대형.. )
    private String company;
    private String companyNation;

    private String carOption;       // 옵션
    private String carColor;
    private Integer carYear;        // 출시 연도
    private Integer carKm;          // 주행거리

    private String gradeName;
    private String gradeDesc;
    private String trimName;
    private String trimNameDesc;

    private RefCarGrade refCarGrade;      // 등급 타입
    private RefCarTrim refCarTrim;      // 등급 타입
    private CarFuelType carFuelType;

    private LocalDate regDate;      // 최초 등록 날짜

}
