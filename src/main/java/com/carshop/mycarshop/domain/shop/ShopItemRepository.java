package com.carshop.mycarshop.domain.shop;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopItemRepository extends JpaRepository<ShopItem, Long> {

    Optional<ShopItem> findByItemName(String ItemName);
}

