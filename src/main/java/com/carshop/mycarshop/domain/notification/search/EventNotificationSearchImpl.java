package com.carshop.mycarshop.domain.notification.search;

import com.carshop.mycarshop.domain.notification.EventNotification;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Log4j2
public class EventNotificationSearchImpl implements EventNotificationSearch {



    @Override
    public Page<EventNotification> searchAll(String[] types, String keyword, Pageable pageable) {



        return null;
    }

    @Override
    public EventNotification searchTodayRandomEvent() {


        return null;
    }


}