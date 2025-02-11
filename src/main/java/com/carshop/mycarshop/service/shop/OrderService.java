package com.carshop.mycarshop.service.shop;

import com.carshop.mycarshop.domain.shop.Order;
import com.carshop.mycarshop.domain.user.User;
import com.carshop.mycarshop.dto.PageRequestDTO;
import com.carshop.mycarshop.dto.PageResponseDTO;
import com.carshop.mycarshop.dto.order.*;
import com.carshop.mycarshop.dto.shop.ItemBuyReqDTO;

import java.util.List;

public interface OrderService {
    Order getOrderInfo(Long orderId);
    PageResponseDTO<OrderResDTO> getOrderAll(User user, PageRequestDTO pageRequestDTO);
    List<OrderItemResDTO> getOrderDetail(User user, Long orderId);
    OrderTemporaryResDTO getOrderTemporary(Long orderTemporaryId);
    OrderDeliveryResDTO getOrderDeliveryProcess(Long orderId);

    Long createOrder(User user, OrderReqDTO orderReqDTO);
    void cancelOrder(Long orderId);
    Long addOrderTemporary(ItemBuyReqDTO itemBuyReqDTO, User user);
}