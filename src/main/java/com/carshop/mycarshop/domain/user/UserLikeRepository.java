package com.carshop.mycarshop.domain.user;

import com.carshop.mycarshop.domain.sellingCar.SellingCar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLikeRepository extends JpaRepository<UserLike, Long> {

    Optional<UserLike> findByUserAndSellingCar(User user, SellingCar sellingCar);
}