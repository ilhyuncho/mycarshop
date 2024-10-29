package com.carshop.mycarshop.domain.buyingCar.search;

import com.carshop.mycarshop.domain.buyingCar.BuyingCar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BuyingCarSearch {
    Page<BuyingCar> searchTest(Pageable pageable);
    BuyingCar findHighProposalPriceInfo(Long sellingCarId);

}