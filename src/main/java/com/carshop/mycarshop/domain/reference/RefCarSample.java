package com.carshop.mycarshop.domain.reference;

import com.carshop.mycarshop.domain.car.CarSize;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="refCarSamples")
@ToString
public class RefCarSample {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="refCarSampleId")
    private Long refCarSampleId;

    private String carNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "refCarInfoId")      // 주 테이블(RefCarSample)에 외래 키
    private RefCarInfo refCarInfo;

//    private String carModel;
//    @Enumerated(EnumType.STRING)
//    private CarSize carGrade;        // 등급 ( 소형, 중형, 대형.. )
//    private String company;
//    private String companyNation;


    private String carOption;       // 옵션
    private String carColor;
    private Integer carYear;      // 출시 연도
    private Integer carKm;          // 주행거리
    private LocalDate regDate;      // 최초 등록 날짜
}
