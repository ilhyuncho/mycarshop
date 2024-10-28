package com.carshop.mycarshop.dto.review;

import com.carshop.mycarshop.dto.PageRequestDTO;
import com.carshop.mycarshop.dto.PageResponseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class ReviewListResDTO<E> extends PageResponseDTO<E> {

    private final float reviewAvgScore;

    @Builder(builderMethodName = "withSuper")
    public ReviewListResDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total, float reviewAvgScore){
        super(pageRequestDTO, dtoList, total);

        this.reviewAvgScore = reviewAvgScore;
    }

}
