package com.carshop.mycarshop.config;

//final 활용. 상수 전용 클래스는 상속·인스턴스화 모두 막는 것이 일반적인 관례
public final class RedisCacheNames {

    public static final String REF_CAR_CONSUMABLES = "refCarConsumables";
    public static final String REF_CAR_SAMPLES = "refCarSamples";
    public static final String SHOP_ITEMS = "shopItems";
    public static final String ACTIVE_EVENTS = "activeEvents";

    private RedisCacheNames() {
    }
}
