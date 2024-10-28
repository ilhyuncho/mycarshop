package com.carshop.mycarshop.domain.notification;

import com.carshop.mycarshop.domain.notification.search.NewsNotificationSearch;

public interface NewsNotificationRepository extends NotificationRepository<NewsNotification, Long>
        , NewsNotificationSearch {

}
