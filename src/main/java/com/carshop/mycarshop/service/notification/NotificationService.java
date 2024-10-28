package com.carshop.mycarshop.service.notification;

import com.carshop.mycarshop.domain.notification.EventNotification;
import com.carshop.mycarshop.domain.notification.EventType;
import com.carshop.mycarshop.dto.ImageOrderReqDTO;
import com.carshop.mycarshop.dto.PageRequestDTO;
import com.carshop.mycarshop.dto.PageResponseDTO;
import com.carshop.mycarshop.dto.notification.NotiEventResDTO;
import com.carshop.mycarshop.dto.notification.NotiNewsResDTO;
import com.carshop.mycarshop.dto.notification.NotiResDTO;
import com.carshop.mycarshop.dto.notification.NotificationRegDTO;

public interface NotificationService {

    PageResponseDTO<NotiEventResDTO> getListEventInfo(PageRequestDTO pageRequestDTO);
    PageResponseDTO<NotiNewsResDTO> getListNewsInfo(PageRequestDTO pageRequestDTO);

    NotiEventResDTO getEventInfo(Long notiId);
    NotiNewsResDTO getNewsInfo(Long notiId);
    NotiResDTO getNotiInfo(Long notiId);

    Long registerEventNotification(NotificationRegDTO eventDTO);
    Long registerNewsNotification(NotificationRegDTO newsDTO);

    void modifyEventNotification(Long notiId, NotificationRegDTO notificationRegDTO);
    void modifyNewsNotification(Long notiId, NotificationRegDTO notificationRegDTO);

    void deleteNotification(String notiType, Long notiId);

    NotiEventResDTO getRandomPopupEventInfo();

    EventNotification getNowDoingEventInfo(EventType ventType);

    void modifyImageOrder(ImageOrderReqDTO imageOrderReqDTO);


}