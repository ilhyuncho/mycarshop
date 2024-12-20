package com.carshop.mycarshop.domain.sellingCar;

import com.carshop.mycarshop.domain.sellingCar.search.SellingCarSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SellingCarRepository extends JpaRepository<SellingCar, Long>, SellingCarSearch {

    Page<SellingCar> findAllBySellingCarStatus(SellingCarStatus sellingCarStatus, Pageable pageable);

    @Query(value = "SELECT * FROM SellingCars WHERE sellingCarStatus = 1 AND uId <> ?2 order by RAND() limit ?1",
            nativeQuery = true)
    List<SellingCar> findRecommendSellingCar(int limit, Long userId);

}