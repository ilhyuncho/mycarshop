package com.carshop.mycarshop.domain.cart;

import com.carshop.mycarshop.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    // Page<Cart> findByUser(User user, Pageable pageable);
    List<Cart> findByUser(User user);
}