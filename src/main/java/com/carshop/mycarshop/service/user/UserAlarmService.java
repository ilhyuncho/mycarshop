package com.carshop.mycarshop.service.user;

import com.carshop.mycarshop.domain.user.User;
import com.carshop.mycarshop.dto.PageRequestDTO;
import com.carshop.mycarshop.dto.PageResponseDTO;
import com.carshop.mycarshop.dto.user.UserAlarmDTO;

public interface UserAlarmService {
    UserAlarmDTO getAlarmInfo(Long alarmId);

    PageResponseDTO<UserAlarmDTO> getListAlarm(PageRequestDTO pageRequestDTO, User user);

    boolean isNewAlarm(User user);

    void registerAlarm(User user, String alarmTitle, String alarmContent);
}