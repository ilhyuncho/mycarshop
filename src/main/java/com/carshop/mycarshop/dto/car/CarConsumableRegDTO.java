package com.carshop.mycarshop.dto.car;

import com.carshop.mycarshop.domain.carConsumable.ConsumableType;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CarConsumableRegDTO {

    private Long refConsumableId;
    private Long consumableId;
    private ConsumableType consumableType;
    private Long carId;

    @Positive   // 양수 체크
    private int replacePrice;
    private int accumKm;
    private int gasLitter;

    @NotEmpty
    private String replaceShop;

    @NotNull
    //@FutureOrPresent
    private LocalDate replaceDate;
}