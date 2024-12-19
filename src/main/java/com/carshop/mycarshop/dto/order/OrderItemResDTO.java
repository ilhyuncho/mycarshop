package com.carshop.mycarshop.dto.order;


import com.carshop.mycarshop.domain.shop.DeliveryStatus;
import com.carshop.mycarshop.dto.ImageListDTO;
import com.carshop.mycarshop.dto.shop.ItemOptionResDTO;
import com.carshop.mycarshop.service.common.CommonUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResDTO extends ImageListDTO {
    private Long orderId;
    private Long orderItemId;
    private DeliveryStatus deliveryStatus;
    private int orderCount;
    private Long shopItemId;
    private String itemName;
    private String itemTitle;
    private int orderPrice;
    private int discountPrice;
    private LocalDate orderDate;
    private Boolean isReviewWrite;      // 리뷰 작성 유무

    @Builder.Default
    private List<ItemOptionResDTO> listItemOption = new ArrayList<>();

    public String getOptionDesc(){
        return CommonUtils.getItemOptionDesc(listItemOption);
    }
    public String getOptionIds(){
        return CommonUtils.getItemOptionIds(listItemOption);
    }


}
