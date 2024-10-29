package com.carshop.mycarshop.domain.user;

public enum PointType {
    TYPE_NONE(0L),
    GAIN(1L),
    CONSUME(2L);

    private final Long type;

    PointType(Long type) {
        this.type = type;
    }
}
