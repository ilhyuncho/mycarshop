package com.carshop.mycarshop.testData.event;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEvent;


@Log4j2
public class SampleMemberCreateEvent extends ApplicationEvent {
    private final String message;
    private final int createMemberCount;

    public SampleMemberCreateEvent(Object source, String message, int createMemberCount) {
        super(source);
        this.message = message;
        this.createMemberCount = createMemberCount;
    }

    public String getMessage() {
        return message;
    }
    public int getCreateMemberCount() {
        return createMemberCount;
    }
}