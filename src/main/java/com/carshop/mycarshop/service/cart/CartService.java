package com.carshop.mycarshop.service.cart;

import com.carshop.mycarshop.domain.cart.Cart;
import com.carshop.mycarshop.domain.user.User;
import com.carshop.mycarshop.dto.cart.CartDetailResDTO;
import com.carshop.mycarshop.dto.shop.ItemBuyReqDTO;

import java.util.List;

public interface CartService {

    List<CartDetailResDTO> getCartAll(User user);
    Long addCart(ItemBuyReqDTO itemBuyReqDTO, User user);
    void modify(ItemBuyReqDTO itemBuyReqDTO);
    Cart deleteInCart(Long cartId);

}