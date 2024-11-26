package com.carshop.mycarshop.domain.sellingCar.search;

import com.carshop.mycarshop.domain.sellingCar.SellingCar;
import com.carshop.mycarshop.dto.PageRequestExtDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SellingCarSearch {
    Page<SellingCar> searchAll(Pageable pageable, PageRequestExtDTO pageRequestExtDT);
}
