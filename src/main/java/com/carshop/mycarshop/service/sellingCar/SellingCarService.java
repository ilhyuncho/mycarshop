package com.carshop.mycarshop.service.sellingCar;

import com.carshop.mycarshop.domain.sellingCar.SellingCar;
import com.carshop.mycarshop.domain.user.User;
import com.carshop.mycarshop.dto.PageRequestExtDTO;
import com.carshop.mycarshop.dto.PageResponseDTO;
import com.carshop.mycarshop.dto.sellingCar.SellingCarRegDTO;
import com.carshop.mycarshop.dto.sellingCar.SellingCarResDTO;

import java.util.List;

public interface SellingCarService {
    SellingCar getSellingCarInfo(Long sellingCarId);
    SellingCarResDTO getSellingCarData(User user, Long sellingCarId);
    PageResponseDTO<SellingCarResDTO> getListSellingCar(PageRequestExtDTO pageRequestExtDT);
    List<SellingCarResDTO> getListRecommend(String memberName);

    void registerSellingCar(User user, SellingCarRegDTO sellingCarRegDTO);
    void likeSellingCar(User user, SellingCarRegDTO sellingCarRegDTO);
    void updateSellingCar(SellingCarRegDTO sellingCarRegDTO);
}