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
    CAR_DETAIL_MODEL_AVATE_THE_NEW(9, "더 뉴 아반떼", "아반떼"),

    CAR_DETAIL_MODEL_K5_DH(10, "K5 DH", "K5"),
    CAR_DETAIL_MODEL_K5_NEW(11, "NEW K5", "K5"),
    CAR_DETAIL_MODEL_K5_ALL_NEW(12, "ALL NEW K5", "K5"),

    CAR_DETAIL_MODEL_K7_AH(13, "K7 AH", "K7"),
    CAR_DETAIL_MODEL_K7_NEW(14, "NEW K7", "K7"),
    CAR_DETAIL_MODEL_K7_ALL_NEW(15, "ALL NEW K7", "K7"),

    CAR_DETAIL_MODEL_K8_SD(18, "K8 SD", "K8"),
    CAR_DETAIL_MODEL_SPORTAGE_DH(19, "스포티지 DH", "스포티지"),
    CAR_DETAIL_MODEL_SPORTAGE_THE_NEW(20, "THE NEW 스포티지", "스포티지"),
    CAR_DETAIL_MODEL_SPORTAGE_ALL_NEW(21, "ALL NEW 스포티지", "스포티지"),

    CAR_DETAIL_MODEL_A_CLASS_5G(22, "A-CLASS 5세대", "A-CLASS"),
    CAR_DETAIL_MODEL_A_CLASS_6G(23, "A-CLASS 6세대", "A-CLASS"),
    CAR_DETAIL_MODEL_A_CLASS_7G(24, "A-CLASS 7세대", "A-CLASS"),
    CAR_DETAIL_MODEL_C_CLASS_4G(25, "C-CLASS 4세대", "C-CLASS"),
    CAR_DETAIL_MODEL_C_CLASS_5G(26, "C-CLASS 5세대", "C-CLASS"),
    CAR_DETAIL_MODEL_C_CLASS_6G(27, "C-CLASS 6세대", "C-CLASS"),
    CAR_DETAIL_MODEL_E_CLASS_5G(28, "E-CLASS 5세대", "E-CLASS"),
    CAR_DETAIL_MODEL_E_CLASS_6G(29, "E-CLASS 6세대", "E-CLASS"),
    CAR_DETAIL_MODEL_E_CLASS_7G(30, "E-CLASS 7세대", "E-CLASS"),
    CAR_DETAIL_MODEL_S_CLASS_7G(31, "S-CLASS 7세대", "S-CLASS"),
    CAR_DETAIL_MODEL_S_CLASS_8G(32, "S-CLASS 8세대", "S-CLASS"),
    CAR_DETAIL_MODEL_S_CLASS_9G(33, "S-CLASS 9세대", "S-CLASS"),

    CAR_DETAIL_MODEL_3SERIES_5G(34, "3-SERIES 5세대", "3-SERIES"),
    CAR_DETAIL_MODEL_3SERIES_6G(35, "3-SERIES 6세대", "3-SERIES"),
    CAR_DETAIL_MODEL_3SERIES_7G(36, "3-SERIES 7세대", "3-SERIES"),
    CAR_DETAIL_MODEL_5SERIES_6G(37, "5-SERIES 6세대", "5-SERIES"),
    CAR_DETAIL_MODEL_5SERIES_7G(38, "5-SERIES 7세대", "5-SERIES"),
    CAR_DETAIL_MODEL_5SERIES_8G(39, "5-SERIES 8세대", "5-SERIES"),
    CAR_DETAIL_MODEL_7SERIES_4G(40, "7-SERIES 4세대", "7-SERIES"),
    CAR_DETAIL_MODEL_7SERIES_5G(41, "7-SERIES 5세대", "7-SERIES"),
    CAR_DETAIL_MODEL_7SERIES_6G(42, "7-SERIES 6세대", "7-SERIES"),
    CAR_DETAIL_MODEL_X5_1G(43, "X5 1세대", "X5"),
    CAR_DETAIL_MODEL_X5_2G(44, "X5 2세대", "X5"),

    CAR_DETAIL_MODEL_MODEL3_1G(45, "MODEL3 1세대", "MODEL3"),
    CAR_DETAIL_MODEL_MODELS_1G(46, "MODELS 1세대", "MODELS"),
    CAR_DETAIL_MODEL_MODELY_1G(47, "MODELY 1세대", "MODELY")

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
