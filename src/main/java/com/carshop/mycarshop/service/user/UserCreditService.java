package com.carshop.mycarshop.service.user;

import com.carshop.mycarshop.domain.user.User;
import com.carshop.mycarshop.dto.user.UserCreditDTO;

public interface UserCreditService {

    Long register(User user, UserCreditDTO userCreditDTO);
    UserCreditDTO readCreditInfo(User user);
}
