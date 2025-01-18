package com.carshop.mycarshop.domain.reference.carType;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum CarFuel {

    FUEL_GASOLINE(1, "가솔린"),
    FUEL_DIESEL(2, "디젤"),
    FUEL_LPG(3, "가스"),
    FUEL_ELECTRIC(4, "하이브리드"),
    FUEL_HYBRID(5, "전기")
    ;

    private final Integer type;
    private final String name;


    CarFuel(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    private final static Map<Integer, CarFuel> typeMap = java.util.Arrays.stream(CarFuel.values())
            .collect(Collectors.toMap(CarFuel::getType, Function.identity()));

    public static CarFuel fromValue(Integer value) {
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
}
