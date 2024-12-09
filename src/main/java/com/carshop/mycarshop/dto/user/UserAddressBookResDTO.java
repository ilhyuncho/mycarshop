package com.carshop.mycarshop.dto.user;


import com.carshop.mycarshop.common.util.Util;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAddressBookResDTO {
    private Long userAddressBookId;
    private String deliveryName;
    private String recipientName;
    private String recipientPhoneNumber;
    private String deliveryRequest;
    private Boolean mainAddressCheck;

    private String zipcode;
    private String cityName;
    private String country;
    private String street;
    private String detailAddress;
    private String fullAddress;

    public String getPhoneNumber(){
        return Util.phoneNumber(this.recipientPhoneNumber);
    }

}
