package com.carshop.mycarshop.service.buyingCar;

import com.carshop.mycarshop.domain.buyingCar.BuyingCar;
import com.carshop.mycarshop.domain.sellingCar.SellingCar;
import com.carshop.mycarshop.domain.user.User;
import com.carshop.mycarshop.dto.PageRequestDTO;
import com.carshop.mycarshop.dto.buyingCar.BuyingCarListResDTO;
import com.carshop.mycarshop.dto.buyingCar.BuyingCarRegDTO;
import com.carshop.mycarshop.dto.buyingCar.BuyingCarViewDTO;

import java.util.List;
import java.util.Optional;

public interface BuyingCarService {

    Optional<BuyingCar> getBuyingCarInfo(User user, SellingCar sellingCar);
    List<BuyingCarViewDTO> getListBuyingCarInfo(User user);
    BuyingCarListResDTO<BuyingCarViewDTO> getPageBuyingCarInfo(PageRequestDTO pageRequestDTO, Long sellingCarId);

    void registerBuyingCar(User user, BuyingCarRegDTO buyingCarRegDTO);
    void updateBuyingCar(User user, BuyingCarRegDTO buyingCarRegDTO);
}
