package com.carshop.mycarshop.service.shop;

import com.carshop.mycarshop.domain.user.User;
import com.carshop.mycarshop.dto.ImageOrderReqDTO;
import com.carshop.mycarshop.dto.shop.ShopItemExtandDTO;
import com.carshop.mycarshop.dto.shop.ShopItemReqDTO;
import com.carshop.mycarshop.dto.shop.ShopItemResDTO;

import java.util.List;

public interface ShopItemService {

    ShopItemExtandDTO getItemInfo(Long itemId, User user);
    List<ShopItemExtandDTO> getAllItems();
    List<ShopItemResDTO> getAllItemsForShop();

    Long registerItem(ShopItemReqDTO shopItemReqDTO);
    void modifyItem(ShopItemReqDTO shopItemReqDTO);
    void modifyImageOrder(ImageOrderReqDTO imageOrderReqDTO);

    void deleteItem(Long itemId);
}
