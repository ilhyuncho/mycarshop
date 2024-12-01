package com.carshop.mycarshop.dto.reference;


import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class RefCarTypeDTO {

    private int typeId;
    private String typeName;
    private int groupIndex;

    private int parentTypeId;
    private int order;
    private int carYearStart;   // 해당 모델 시작 연도
    private int carYearEnd;     // 해당 모델 시작 마지막 연도
}
