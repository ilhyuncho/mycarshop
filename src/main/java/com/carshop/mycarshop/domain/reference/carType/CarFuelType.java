package com.carshop.mycarshop.domain.reference.carType;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum CarFuelType {
    FUEL_DEFAULT(0, "연료 기본","연료 기본"),
    FUEL_GASOLINE(1, "가솔린", "가솔린1"),
    FUEL_DIESEL(2, "디젤","디젤1" ),
    FUEL_LPG(3, "가스","가스1" ),
    FUEL_ELECTRIC(4, "전기","전기1" ),
    FUEL_HYBRID(5, "하이브리드","하이브리드1" )
    ;

    private final Integer type;
    private final String name;
    private final String nameDesc;


    CarFuelType(Integer type, String name, String nameDesc) {
        this.type = type;
        this.name = name;
        this.nameDesc = nameDesc;
    }

    private final static Map<Integer, CarFuelType> typeMap = java.util.Arrays.stream(CarFuelType.values())
            .collect(Collectors.toMap(CarFuelType::getType, Function.identity()));

    public static CarFuelType fromValue(Integer value) {
        return Optional.ofNullable(value)
                .map(typeMap::get)
                .orElseThrow(() -> new IllegalArgumentException("value is not valid"));
    }

    public Integer getType(){
        return type;
    }
    public String getName(){
        return name;
    }
    public String getNameDesc(){
        return nameDesc;
    }
}