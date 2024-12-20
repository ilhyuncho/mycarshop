package com.carshop.mycarshop.domain.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum UserGradeType {
    GRADE_NONE(0L, "등급 없음", "노 등급"),
    GRADE_A(1L, "등급 A", "일반 회원"),    // 일반 회원
    GRADE_E(2L, "등급 E", "우수 회원"),
    GRADE_S(3L, "등급 S", "VIP 회원");

    private final Long type;
    private final String typeName;
    private final String gardeDesc;

    private final static Map<Long, UserGradeType> typeMap = Arrays.stream(UserGradeType.values())
            .collect(Collectors.toMap(UserGradeType::getType, Function.identity()));

    UserGradeType(Long type, String typeName, String gardeDesc) {
        this.type = type;
        this.typeName = typeName;
        this.gardeDesc = gardeDesc;
    }

    @JsonCreator
    public static UserGradeType fromValue(Long value) {
        return Optional.ofNullable(value)
                .map(typeMap::get)
                .orElseThrow(() -> new IllegalArgumentException("value is not valid"));
    }
    @JsonValue
    public Long getType(){
        return type;
    }

    public String getTypeName(){return typeName;}
    public String getGardeDesc(){return gardeDesc;}

}