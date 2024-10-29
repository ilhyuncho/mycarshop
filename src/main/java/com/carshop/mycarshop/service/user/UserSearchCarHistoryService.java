package com.carshop.mycarshop.service.user;

import com.carshop.mycarshop.domain.sellingCar.SellingCar;
import com.carshop.mycarshop.domain.user.User;
import com.carshop.mycarshop.dto.sellingCar.SellingCarResDTO;

import java.util.List;

public interface UserSearchCarHistoryService {

    void insertSearchCarHistory(User user, SellingCar sellingCar);

    List<SellingCarResDTO> getSearchCarHistory(User user);
}
