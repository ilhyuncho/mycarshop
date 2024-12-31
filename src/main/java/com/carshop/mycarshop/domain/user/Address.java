package com.carshop.mycarshop.domain.user;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable         // 임베디드 타입 (복합값) - composite date type ( 복합적인 데이터 값들을 여러개 저장 )
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@AllArgsConstructor
@Builder
public class Address implements Serializable {

    //@NotEmpty
    private City city;

    //@NotEmpty
    private String street;

    //@NotEmpty
    private String detailAddress;

    public String fullAddress(){
        return city.getCountry() + " " + city.getCityName() + " " + street + " " + detailAddress;
    }

// 같을 기준으로 인스턴스를 비교하려면 필요 함
//    @Override
//    public int hashCode() {
//        return super.hashCode();
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        return super.equals(obj);
//    }


}