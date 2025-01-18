package com.carshop.mycarshop.dto.sellingCar;
import com.carshop.mycarshop.common.util.Util;
import com.carshop.mycarshop.domain.buyingCar.BuyCarStatus;
import com.carshop.mycarshop.domain.reference.carType.CarFuel;
import com.carshop.mycarshop.domain.sellingCar.SellType;
import com.carshop.mycarshop.domain.sellingCar.SellingCarStatus;
import com.carshop.mycarshop.dto.ImageListDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class SellingCarResDTO extends ImageListDTO {
    private Long carId;
    private String carNumber;
    private String carDetailModel;
    private String carCompany;
    private Integer carYears;
    private int carKm;
    private CarFuel carFuel;
    private Boolean isLike;
    private Integer requiredPrice;
    private Long sellingCarId;
    private Integer viewCount;

    private BuyCarStatus buyCarStatus;
    private SellingCarStatus sellingCarStatus;

    private SellType sellType;
    private Integer proposalPrice;          // 경매 응찰한 가격

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expiredDate;
    public boolean isExpired(){
        return Util.isTimeOver(expiredDate);
    }
    public String getCarFuel() {return carFuel.getName();}
}
