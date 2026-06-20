package com.carshop.mycarshop.dto.shop;

import com.carshop.mycarshop.domain.shop.ItemSellingStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ShopItemExtandDTO extends ShopItemResDTO {
    private Integer stockCount;         // 재고수량
    private Integer purchaseCount;      // 구매수량
    private Integer membershipPercent;
    private Boolean isEventTarget;

    @Builder.Default
    private List<ItemOptionDTO> listOptionType= new ArrayList<>();

    public ItemOptionDTO getOptionType(Integer index){
        return listOptionType.get(index);
    }
    // Jackson은 인자 없는 getXxx() / isXxx() 만 JSON 프로퍼티로 봅니다.
    @JsonIgnore     // "sellingStatus" JSON에 포함 → 역직렬화 문제
    public ItemSellingStatus getSellingStatus(){
        return this.stockCount <= 0 ? ItemSellingStatus.STATUS_SOLDOUT : ItemSellingStatus.STATUS_SELLING;
    }
}
