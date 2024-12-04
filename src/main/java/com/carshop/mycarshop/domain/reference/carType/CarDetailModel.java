package com.carshop.mycarshop.domain.reference.carType;


import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;


public enum CarDetailModel implements Operation{
    CAR_DETAIL_MODEL_SONATA_ADGE(1, "쏘나타 디 엣지", "쏘나타"),
    CAR_DETAIL_MODEL_SONATA_NEW_RISE(2, "쏘나타 뉴라이즈", "쏘나타"),
    CAR_DETAIL_MODEL_SONATA_LF(3, "LF 쏘나타", "쏘나타"),
    CAR_DETAIL_MODEL_TUCSON_THE_NEW(4, "더 뉴 투싼", "투싼"),
    CAR_DETAIL_MODEL_TUCSON_ALL_NEW(5, "올 뉴 투싼", "투싼"),
    CAR_DETAIL_MODEL_TUCSON(6, "투싼", "투싼"),
    CAR_DETAIL_MODEL_AVANTE_THE_NEW(7, "더 뉴 아반떼", "아반떼"),
    CAR_DETAIL_MODEL_AVANTE_AD(8, "아반떼 AD", "아반떼"),
    CAR_DETAIL_MODEL_AVATE_THE_NEW(9, "더 뉴 아반떼", "아반떼")
    ;

    private final Integer type;
    private final String name;
    private final String parentMenuName;

    CarDetailModel(Integer type, String name, String parentMenuName) {
        this.type = type;
        this.name = name;
        this.parentMenuName = parentMenuName;
    }

    private final static Map<Integer, CarDetailModel> typeMap = java.util.Arrays.stream(CarDetailModel.values())
            .collect(Collectors.toMap(CarDetailModel::getType, Function.identity()));
    public static CarDetailModel fromValue(Integer value) {
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
    @Override
    public String getParentMenuName() {
        return parentMenuName;
    }
}
