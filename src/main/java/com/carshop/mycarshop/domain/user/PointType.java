package com.carshop.mycarshop.domain.user;

public enum PointType {
    NONE(0, "기본 타입"),
    GAIN(1, "획득"),
    CONSUME(2, "소비"),
    RETURN(3, "환불")
    ;

    private final int type;
    private final String name;

    PointType(int type, String name ) {
        this.type = type;
        this.name = name;
    }
    public int getType(){
        return type;
    }
    public String getName(){
        return name;
    }
}
