package com.carshop.mycarshop.domain.carConsumable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReplaceAlarm {
    NOT_CYCLE(0), READY_CYCLE(1);

    private final int type;
}
