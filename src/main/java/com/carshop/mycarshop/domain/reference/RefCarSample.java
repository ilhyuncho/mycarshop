package com.carshop.mycarshop.domain.reference;

import com.carshop.mycarshop.domain.reference.carType.CarFuelType;
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
    private String carOption;       // 옵션
    private String carColor;
    private Integer carYear;        // 출시 연도
    private Integer carKm;          // 주행거리

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "refCarInfoId")      // 주 테이블(RefCarSample)에 외래 키
    private RefCarInfo refCarInfo;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "refCarGradeId")      // 주 테이블(RefCarSample)에 외래 키
    private RefCarGrade refCarGrade;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "refCarTrimId")      // 주 테이블(RefCarSample)에 외래 키
    private RefCarTrim refCarTrim;

    private CarFuelType carFuelType;

    private LocalDate regDate;      // 최초 등록 날짜
}
