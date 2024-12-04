package com.carshop.mycarshop.domain.reference.carType;


import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;


public enum CarCompany implements Operation{
    CAR_COMPANY_HUNDAI(1, "현대", "국산"),
    CAR_COMPANY_KIA(2, "기아", "국산"),
    CAR_COMPANY_KGM(3, "KGM", "국산"),
    CAR_COMPANY_BENZ(4, "벤츠", "수입"),
    CAR_COMPANY_BMW(5, "BMW", "수입"),
    CAR_COMPANY_TESLA(6, "테슬라", "수입")
    ;

    private final Integer type;
    private final String name;
    private final String parentMenuName;

    CarCompany(Integer type, String name, String parentMenuName) {
        this.type = type;
        this.name = name;
        this.parentMenuName = parentMenuName;
    }

    private final static Map<Integer, CarCompany> typeMap = java.util.Arrays.stream(CarCompany.values())
            .collect(Collectors.toMap(CarCompany::getType, Function.identity()));

    public static CarCompany fromValue(Integer value) {
        return Optional.ofNullable(value)
                .map(typeMap::get)
                .orElseThrow(() -> new IllegalArgumentException("value is not valid"));
    }

    @Override
    public Integer getType(){
        return type;
    }
    @Override
    public String getName(){
        return name;
    }

    @Override
    public String getParentMenuName() {
        return parentMenuName;
    }
}
