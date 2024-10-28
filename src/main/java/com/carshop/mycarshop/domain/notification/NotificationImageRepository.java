package com.carshop.mycarshop.domain.notification;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationImageRepository extends JpaRepository<NotificationImage, Long> {

    List<NotificationImage> findByNotification(Notification notification);
}
