package com.carshop.mycarshop.dto.car;


import com.carshop.mycarshop.domain.carConsumable.CarConsumable;
import com.carshop.mycarshop.domain.carConsumable.ReplaceAlarm;
import com.carshop.mycarshop.domain.reference.RefCarConsumable;
import com.carshop.mycarshop.service.common.CommonUtils;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CarConsumableResDTO {

    private Long refConsumableId;
    private String name;
    private String repairType;
    private int replaceCycleKm;
    private int replaceCycleMonth;
    private int replacePrice;
    private int accumKm;
    private String replaceShop;
    private LocalDate replaceDate;
    private int viewOrder;

    @Enumerated(EnumType.STRING)
    private ReplaceAlarm replaceAlarm;

    // 최근 정비 내역 set
    public void setRecentReplaceInfo(RefCarConsumable refCarConsumable, CarConsumable carConsumable){

        this.setReplaceAlarm(CommonUtils.checkNextReplaceDay(refCarConsumable, carConsumable));

        this.setReplaceInfo(carConsumable.getReplacePrice(), carConsumable.getAccumKm(),
                carConsumable.getReplaceShop(), carConsumable.getReplaceDate() );
    }

    public void setReplaceInfo(int replacePrice, int accumKm, String replaceShop, LocalDate replaceDate){
        this.replacePrice = replacePrice;
        this.replaceShop = replaceShop;
        this.accumKm = accumKm;
        this.replaceDate = replaceDate;
    }

    public void setReplaceAlarm(ReplaceAlarm replaceAlarm){
        this.replaceAlarm = replaceAlarm;
    }
}