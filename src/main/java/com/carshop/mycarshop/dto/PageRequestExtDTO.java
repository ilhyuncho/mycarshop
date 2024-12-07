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

    public String getTypeGroup1() {
        if(typeGroup1 != null && !typeGroup1.isEmpty()){
            return typeGroup1;
        }
        return null;
    }
    public String getTypeGroup2() {
        if(typeGroup2 != null && !typeGroup2.isEmpty()){
            return typeGroup2;
        }
        return null;
    }
    public String getTypeGroup3() {
        if(typeGroup3 != null && !typeGroup3.isEmpty()){
            return typeGroup3;
        }
        return null;
    }
    public String getTypeGroup4() {
        if(typeGroup4 != null && !typeGroup4.isEmpty()){
            return typeGroup4;
        }
        return null;
    }

}
