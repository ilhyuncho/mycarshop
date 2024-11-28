package com.carshop.mycarshop.domain.car;

import com.carshop.mycarshop.domain.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="refCarTypes")
public class RefCarType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="refCarTypeId")
    private Long refCarTypeId;

    private int typeId;
    private String typeName;
    private int groupIndex;

    private int parentTypeId;
    private int order;
    private int carYearStart;   // 해당 모델 시작 연도
    private int carYearEnd;     // 해당 모델 시작 마지막 연도
}
