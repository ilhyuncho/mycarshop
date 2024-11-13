package com.carshop.mycarshop.config.event;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
public class EventConfig {

    // 스프링의 이벤트 퍼블리셔 와 리스너를 다른 쓰레드로 실행 시키기 위해서
    // 이메일 발송을 별도의 쓰레드에서 비동기로 처리
    @Bean(name= "applicationEventMulticaster")
    public ApplicationEventMulticaster simpleApplicationEventMulticaster(){
        SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();

        eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());

        return eventMulticaster;
    }
}
