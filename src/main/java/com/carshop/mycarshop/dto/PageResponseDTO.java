package com.carshop.mycarshop.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseDTO<E> {
    private int page;
    private int size;
    private int total;

    private int start;  // 시작 페이지 번호
    private int end;

    private boolean prev;   // 이전 페이지 존재 여부
    private boolean next;

    private List<E> dtoList;

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total){

        if(total <= 0){
            return;
        }

        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        this.total = total;
        this.dtoList = dtoList;

        this.end = (int)(Math.ceil(this.page / 5.0)) * 5;     // 화면에서의 마지막 번호
        this.start = this.end - 4;  // 화면에서의 시작 번호

        int last = (int)(Math.ceil((total/(double)size)));      // 데이터의 개수를 계산한 마지막 페이지 번호

        this.end = Math.min(end, last);
        this.prev = this.start > 1;
        this.next = total > this.end * this.size;

    }

}
