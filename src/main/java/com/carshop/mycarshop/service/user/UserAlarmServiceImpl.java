package com.carshop.mycarshop.service.user;

import com.carshop.mycarshop.common.exception.ItemNotFoundException;
import com.carshop.mycarshop.domain.user.User;
import com.carshop.mycarshop.domain.user.UserAlarm;
import com.carshop.mycarshop.domain.user.UserAlarmRepository;
import com.carshop.mycarshop.dto.PageRequestDTO;
import com.carshop.mycarshop.dto.PageResponseDTO;
import com.carshop.mycarshop.dto.user.UserAlarmDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class UserAlarmServiceImpl implements UserAlarmService {

    private final UserAlarmRepository userAlarmRepository;

    @Override
    public UserAlarmDTO getAlarmInfo(Long alarmId) {

        UserAlarm userAlarm = userAlarmRepository.findById(alarmId)
                .orElseThrow(() -> new ItemNotFoundException("해당 알림 정보가 존재하지않습니다"));

        // 읽음으로 표시
        userAlarm.readAlarm();

        return entityToDTO(userAlarm);
    }

    @Override
    public PageResponseDTO<UserAlarmDTO> getListAlarm(User user, PageRequestDTO pageRequestDTO){

        Pageable pageable = pageRequestDTO.getPageable("userAlarmId");

        Page<UserAlarm> result = userAlarmRepository.findByUser(user, pageable);

        List<UserAlarmDTO> listAlarmDTO = result.getContent()
                .stream()
                .map(UserAlarmServiceImpl::entityToDTO)
                .collect(Collectors.toList());

        return PageResponseDTO.<UserAlarmDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(listAlarmDTO)
                .total((int)result.getTotalElements())
                .build();
    }

    @Override
    public boolean isNewAlarm(User user) {
        return userAlarmRepository.countByUserAndAlarmCheck(user,false) > 0;
    }

    @Override
    public void registerAlarm(User user, String alarmTitle, String alarmContent) {

        UserAlarm userAlarm = UserAlarm.builder()
                .user(user)
                .alarmTitle(alarmTitle)
                .alarmContent(alarmContent)
                .alarmCheck(false)
                .build();

        userAlarmRepository.save(userAlarm);
    }

    private static UserAlarmDTO entityToDTO(UserAlarm userAlarm) {
        return UserAlarmDTO.builder()
                .userAlarmID(userAlarm.getUserAlarmId())
                .alarmTitle(userAlarm.getAlarmTitle())
                .alarmContent(userAlarm.getAlarmContent())
                .alarmCheck(userAlarm.isAlarmCheck())
                .regDate(userAlarm.getRegDate())
                .build();
    }


}
