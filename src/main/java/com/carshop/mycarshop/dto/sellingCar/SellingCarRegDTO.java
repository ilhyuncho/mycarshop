package com.carshop.mycarshop.dto.sellingCar;

import com.carshop.mycarshop.domain.sellingCar.SellingCarStatus;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SellingCarRegDTO {

    @NotNull
    private Long carId;
    private String sellType;
    private int requiredPrice;
    private Boolean isLike;
    private SellingCarStatus sellingCarStatus;   // 판매 중인 차량 취소 or 완료 전달
}
