package com.carshop.mycarshop.domain.reference;

import com.carshop.mycarshop.domain.car.CarSize;
import com.carshop.mycarshop.domain.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="refCarInfos")
public class RefCarInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="refCarInfoId")
    private Long refCarInfoId;

    private String carModel;
    private String company;
    private String companyNation;

    private CarSize carGrade;        // 등급 ( 소형, 중형, 대형.. )
    private int carYearStart;        // 해당 모델 시작 연도
    private int carYearEnd;          // 해당 모델 시작 마지막 연도
    private String carOption;
}
