package com.carshop.mycarshop.domain.reference;

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
@Table(name="refCarTrims")
public class RefCarTrim{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="refCarTrimId")
    private Long refCarTrimId;

    private String name;
    private String nameDesc;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "refCarGradeId")
    RefCarGrade refCarGrade;

}
