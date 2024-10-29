package com.carshop.mycarshop.service.shop;

import com.carshop.mycarshop.dto.shop.ItemOptionResDTO;

import java.util.List;

public interface ItemOptionService {
    List<ItemOptionResDTO> getListItemOptionInfo(List<Long> listOptionId);
}
