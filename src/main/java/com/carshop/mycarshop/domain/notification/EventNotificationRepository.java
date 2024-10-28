package com.carshop.mycarshop.domain.notification;

import com.carshop.mycarshop.domain.notification.search.EventNotificationSearch;

import java.util.List;

public interface EventNotificationRepository extends NotificationRepository<EventNotification, Long>
        , EventNotificationSearch {

    List<EventNotification> findByEventType(EventType eventType);

}