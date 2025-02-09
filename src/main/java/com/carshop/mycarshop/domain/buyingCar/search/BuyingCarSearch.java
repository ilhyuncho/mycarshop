package com.carshop.mycarshop.domain.buyingCar.search;

import com.carshop.mycarshop.domain.buyingCar.BuyingCar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BuyingCarSearch {
    Page<BuyingCar> searchTest(Pageable pageable);
   // Optional<BuyingCar> findHighProposalPriceInfo(Long sellingCarId);

}