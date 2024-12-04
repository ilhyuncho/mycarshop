package com.carshop.mycarshop.domain.reference.carType;


import lombok.extern.log4j.Log4j2;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum CarCategory implements Operation{
    //CAR_DEFAULT(0, "제조사"),
    CAR_DOMESTIC(1, "국산"),
    CAR_IMPORTED(2, "수입");       // 국산차 OR 수입차

    private final Integer type;
    private final String name;

    CarCategory(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    private final static Map<Integer, CarCategory> typeMap = java.util.Arrays.stream(CarCategory.values())
            .collect(Collectors.toMap(CarCategory::getType, Function.identity()));

    public static CarCategory fromValue(Integer value) {
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
        return "";
    }
}
