package com.carshop.mycarshop.domain.reference.carType;


public interface Operation {

    Integer getType();
    String getName();
    String getParentMenuName();

    //List<Operation> except(); // 필터링 용
}
