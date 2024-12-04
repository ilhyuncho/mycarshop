package com.carshop.mycarshop.domain.reference.carType;

import com.carshop.mycarshop.dto.reference.RefCarTypeDTO;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum CarTypeGroup {
    CAR_TYPE_GROUP1(0, "CarCategory", CarCategory.class),
    CAR_TYPE_GROUP2(1, "CarCompany", CarCompany.class),
    CAR_TYPE_GROUP3(2, "CarModel", CarModel.class),
    CAR_TYPE_GROUP4(3, "CarDetailModel", CarDetailModel.class);

    private final Integer type;
    private final String name;
    private final Class<? extends Operation> cls;

    private final static Map<Integer, CarTypeGroup> typeMap = java.util.Arrays.stream(CarTypeGroup.values())
            .collect(Collectors.toMap(CarTypeGroup::getType, Function.identity()));

    CarTypeGroup(int type, String name, Class<? extends Operation> cls) {
        this.type = type;
        this.name = name;
        this.cls = cls;
    }

    public static CarTypeGroup fromValue(Integer value) {

        return Optional.ofNullable(value)
                .map(typeMap::get)
               // .orElseThrow(() -> new IllegalArgumentException("value is not valid"));
                .orElse(null);
    }

    public static List<RefCarTypeDTO> buildMetaDetailTypeDto(Class<? extends Operation> cls, String parentMenuName) {
        Operation[] enumConstants = cls.getEnumConstants();

        return Arrays.stream(enumConstants)
                .filter(code -> code.getParentMenuName().equals(parentMenuName))
                .map(code -> RefCarTypeDTO.builder()
                        .typeId(code.getType())
                        .typeName(code.getName())
                        .build())
                .collect(Collectors.toList());
    }

    public Integer getType(){
        return type;
    }
    public String getName(){
        return name;
    }
    public Class<? extends Operation> getTypeClass() {
        return cls;
    }
}
