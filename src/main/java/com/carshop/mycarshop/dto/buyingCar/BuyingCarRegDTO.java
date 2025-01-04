package com.carshop.mycarshop.dto.buyingCar;

import com.carshop.mycarshop.domain.buyingCar.BuyCarStatus;
import com.carshop.mycarshop.domain.user.User;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BuyingCarRegDTO {
    private Long carId;
    private Long sellingCarId;
    private int requestPrice;
    private String phoneNumber;
    private String offerType;
    private String consultText;

    public String getAlarmContent(User user) {

        String alarmContent = "알람이 도착 했습니다";

        BuyCarStatus buyCarStatus = BuyCarStatus.fromValue(this.offerType);

        if (buyCarStatus.equals(BuyCarStatus.AUCTION_REQUEST)) {
            alarmContent = user.getMember().getMemberId() + " 고객님께서 경매에 참여 하였습니다\n" +
                    "입찰 금액은 " + String.format("%,d", requestPrice) + "원 입니다";
        } else if (buyCarStatus.equals(BuyCarStatus.CONSULT_REQUEST)){
            alarmContent = consultText;
        }
        return alarmContent;
    }

}
