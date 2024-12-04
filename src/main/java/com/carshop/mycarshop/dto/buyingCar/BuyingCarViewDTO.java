package com.carshop.mycarshop.dto.buyingCar;
import com.carshop.mycarshop.domain.buyingCar.BuyCarStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BuyingCarViewDTO {

    private String userName;
    private String memberId;
    private int proposalPrice;
    private BuyCarStatus buyCarStatus;
    private String carNumber;
    private String carDetailModel;
    private Long carId;
    private Long sellingCarId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registerTime;

}
