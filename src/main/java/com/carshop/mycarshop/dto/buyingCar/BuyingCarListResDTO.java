package com.carshop.mycarshop.dto.buyingCar;

import com.carshop.mycarshop.dto.PageRequestDTO;
import com.carshop.mycarshop.dto.PageResponseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString(callSuper = true)
public class BuyingCarListResDTO<E> extends PageResponseDTO<E> {

    int highProposalPrice;

    @Builder(builderMethodName = "withSuper")
    public BuyingCarListResDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total,
                               int highProposalPrice ) {
        super(pageRequestDTO, dtoList, total);

        this.highProposalPrice = highProposalPrice;
    }
}
