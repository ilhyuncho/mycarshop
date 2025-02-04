package com.carshop.mycarshop.domain.reference;

import com.carshop.mycarshop.domain.reference.carType.CarDetailModel;
import com.carshop.mycarshop.domain.reference.carType.CarFuelType;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Table(name="refCarGrades")
public class RefCarGrade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="refCarGradeId")
    private Long refCarGradeId;

    private String name;
    private String nameDesc;

    private CarFuelType carFuelType;

    @Enumerated(EnumType.STRING)
    private CarDetailModel carDetailModel;

    @OneToMany(mappedBy = "refCarGrade", //
            cascade = {CascadeType.ALL}, // SellingCar 엔티티에서 하위 엔티티 객체들을 관리 하는 기능을 추가 해서 사용
            fetch = FetchType.EAGER,
            orphanRemoval = true        // 하위 엔티티가 참조가 더 이상 없는 상태면 삭제 처리 해준다
    )
    //@Builder.Default
    //@Singular("carTrimSet")
    //@BatchSize(size=20) // N번에 해당하는 쿼리를 모아서 한번에 실행, (N+1문제 해결)
    private Set<RefCarTrim> carTrimSet = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "refCarInfoId")
    private RefCarInfo refCarInfo;

    public void setRefCarInfo(RefCarInfo refCarInfo){
        this.refCarInfo = refCarInfo;
    }


    public RefCarGrade(String name, String nameDesc, CarFuelType carFuelType, CarDetailModel carDetailModel, Set<RefCarTrim> carTrimSet){
        this.name = name;
        this.nameDesc = nameDesc;

        this.carFuelType = carFuelType;
        this.carDetailModel = carDetailModel;

        this.carTrimSet.addAll(carTrimSet);

        carTrimSet.forEach(a->{
            a.refCarGrade = this;
        });
    }
}
