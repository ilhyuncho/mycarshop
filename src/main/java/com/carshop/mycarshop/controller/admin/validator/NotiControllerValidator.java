package com.carshop.mycarshop.controller.admin.validator;

import com.carshop.mycarshop.common.util.Util;
import com.carshop.mycarshop.dto.notification.NotificationRegDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class NotiControllerValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return NotificationRegDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        NotificationRegDTO notificationRegDTO = (NotificationRegDTO)target;

        // 테스트용
        if(notificationRegDTO.getName().isEmpty()){
            notificationRegDTO.setName(Util.createRandomName(notificationRegDTO.getTarget()));
        }
    }
}