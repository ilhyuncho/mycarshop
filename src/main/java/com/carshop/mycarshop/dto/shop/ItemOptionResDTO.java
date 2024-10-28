package com.carshop.mycarshop.dto.shop;


import lombok.*;

@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
public class ItemOptionResDTO {
    private Long itemOptionId;
    private String optionType;
    private String optionName;
}
