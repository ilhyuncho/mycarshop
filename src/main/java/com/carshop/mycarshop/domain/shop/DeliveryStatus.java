package com.carshop.mycarshop.domain.shop;

public enum DeliveryStatus {
    DELIVERY_NONE(0,"배송 없음"),
    DELIVERY_PREPARE(1,"배송 준비중"),
    DELIVERY_SHIPPING(2,"배송중"),
    DELIVERY_COMPLETE(3, "배송 완료"),
    DELIVERY_CANCEL(4, "주문 취소");

    private final Integer type;
    private final String name;

    DeliveryStatus(Integer type, String name) {
        this.type = type;
        this.name = name;
    }
    public int getType(){return type;}
    public String getName(){return name;}
}
