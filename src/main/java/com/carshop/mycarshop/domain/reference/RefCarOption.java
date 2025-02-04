package com.carshop.mycarshop.domain.reference;

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
@Table(name="refCarOptions")
public class RefCarOption extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="refCarOptionId")
    private Long refCarOptionId;

    private String optionName;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "refCarInfoId")
//    RefCarInfo refCarInfo;

    private String optionDesc;
}
