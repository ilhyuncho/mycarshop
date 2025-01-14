package com.carshop.mycarshop.domain.reference.carType;


import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;


public enum CarModel implements Operation{
    CAR_MODEL_SONATA(1, "쏘나타", "현대"),
    CAR_MODEL_TUCSON(2, "투싼", "현대"),
    CAR_MODEL_AVANTE(3, "아반떼", "현대"),
    CAR_MODEL_K5(4, "K5","기아"),
    CAR_MODEL_K7(5, "K7", "기아"),
    CAR_MODEL_K8(6, "K8", "기아"),
    CAR_MODEL_SPORTAGE(7, "스포티지", "기아"),


    CAR_MODEL_A_CLASS(8, "A-CLASS", "벤츠"),
    CAR_MODEL_C_CLASS(9, "C-CLASS", "벤츠"),
    CAR_MODEL_E_CLASS(10, "E-CLASS", "벤츠"),
    CAR_MODEL_S_CLASS(11, "S-CLASS", "벤츠"),
    CAR_MODEL_3_SERIES(12, "3-SERIES", "BMW"),
    CAR_MODEL_5_SERIES(13, "5-SERIES", "BMW"),
    CAR_MODEL_7_SERIES(14, "7-SERIES", "BMW"),
    CAR_MODEL_X5(15, "X5", "BMW"),
    CAR_MODEL_MODEL_3(16, "MODEL3", "테슬라"),
    CAR_MODEL_MODEL_S(17, "MODELS", "테슬라"),
    CAR_MODEL_MODEL_Y(18, "MODELY", "테슬라"),

    ;

    private final Integer type;
    private final String name;
    private final String parentMenuName;

    CarModel(Integer type, String name, String parentMenuName) {
        this.type = type;
        this.name = name;
        this.parentMenuName = parentMenuName;
    }

    private final static Map<Integer, CarModel> typeMap = java.util.Arrays.stream(CarModel.values())
            .collect(Collectors.toMap(CarModel::getType, Function.identity()));
    public static CarModel fromValue(Integer value) {
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
