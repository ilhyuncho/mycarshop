package com.carshop.mycarshop.domain.reference;

import com.carshop.mycarshop.domain.car.CarSize;
import com.carshop.mycarshop.domain.common.BaseEntity;
import com.carshop.mycarshop.domain.reference.carType.CarFuel;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="refCarInfos")
@Log4j2
public class RefCarInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="refCarInfoId")
    private Long refCarInfoId;

    private String carCategory;        // 국산차 or 수입차
    private String carModel;
    private String carDetailModel;
    private String company;
    private String companyNation;

    private CarSize carGrade;        // 등급 ( 소형, 중형, 대형.. )
    private int carYearStart;        // 해당 모델 시작 연도
    private int carYearEnd;          // 해당 모델 시작 마지막 연도

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "RefCarFuelInfos", joinColumns = @JoinColumn(name = "refCarInfoId"))
    @Builder.Default
    private Set<CarFuel> fuel = new HashSet<>();

    //private String carOption;
    @OneToMany(mappedBy = "refCarInfo", // RefCarOption의 RefCarInfo변수
            cascade = {CascadeType.ALL}, // RefCarInfo 엔티티에서 하위 엔티티 객체들을 관리 하는 기능을 추가 해서 사용
            fetch = FetchType.LAZY,
            orphanRemoval = true        // 하위 엔티티가 참조가 더 이상 없는 상태면 삭제 처리 해준다
    )
    @Builder.Default
    @BatchSize(size=20) // N번에 해당하는 쿼리를 모아서 한번에 실행, (N+1문제 해결)
    private Set<RefCarOption> refCarOptionSet = new HashSet<>();

    public void setRefCarOptions(Set<RefCarOption> refCarOptionSet){

        this.refCarOptionSet = refCarOptionSet;
        refCarOptionSet.forEach( carOption -> {
            carOption.refCarInfo = this;
        });
    }

}
