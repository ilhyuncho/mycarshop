package com.carshop.mycarshop.dto;


import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class PageRequestExtDTO extends PageRequestDTO{
    public PageRequestExtDTO(int page, int size, String type, String keyword, String link, String typeExt) {
        super(page, size, type, keyword, link);
        this.typeExt = typeExt;
    }

    private String typeExt;        // 검색의 종류 a(경매), c(상담)

    private String typeGroup1;
    private String typeGroup2;
    private String typeGroup3;
    private String typeGroup4;

    private int carYearsMin;          // 차 검색 최소 연식
    private int carYearsMax;          // 차 검색 최대 연식
    public String[] getTypeExts(){
        if(typeExt == null || typeExt.isEmpty()){
            return null;
        }
        return typeExt.split("");
    }


}
