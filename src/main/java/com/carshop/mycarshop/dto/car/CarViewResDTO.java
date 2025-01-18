package com.carshop.mycarshop.dto.car;

import com.carshop.mycarshop.domain.reference.carType.CarFuel;
import com.carshop.mycarshop.domain.sellingCar.SellType;
import com.carshop.mycarshop.domain.sellingCar.SellingCarStatus;
import com.carshop.mycarshop.dto.ImageListDTO;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CarViewResDTO extends ImageListDTO {        // 내차 정보 로딩 시

    private Long carId;
    private String userName;
    private String memberId;

    @Pattern(regexp = "[0-9]{2,3}[가-힣][0-9]{4}$")
    private String  carNumber;

    private String carSize;

    @NotEmpty
    private String  carDetailModel;

    @NotNull
    private int     carYears;

    @NotEmpty
    private String  carColors;

    @NotNull
    private int     carKm;

    @NotNull
    private CarFuel carFuel;

    private Long sellingCarId;
    private SellingCarStatus sellingCarStatus;
    private SellType sellType;                      // 차량 판매 방식

    @Builder(builderMethodName = "writeCarViewDTOBuilder")
    public CarViewResDTO(Long carId, String userName, String memberId, String carNumber, String carSize, String carDetailModel, int carYears,
                         String carColors, int carKm, CarFuel carFuel) {
        this.carId = carId;
        this.userName = userName;
        this.memberId = memberId;
        this.carNumber = carNumber;
        this.carSize = carSize;
        this.carDetailModel = carDetailModel;
        this.carYears = carYears;
        this.carColors = carColors;
        this.carKm = carKm;
        this.carFuel = carFuel;
    }
}