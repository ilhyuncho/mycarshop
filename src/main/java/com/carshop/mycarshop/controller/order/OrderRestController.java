package com.carshop.mycarshop.controller.order;

import com.carshop.mycarshop.domain.user.User;
import com.carshop.mycarshop.dto.order.OrderReqDTO;
import com.carshop.mycarshop.dto.shop.ItemBuyReqDTO;
import com.carshop.mycarshop.service.shop.OrderService;
import com.carshop.mycarshop.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Log4j2
@PreAuthorize("hasRole('USER')")
public class OrderRestController {
    private final OrderService orderService;
    private final UserService userService;

    @ApiOperation(value = "상품 결제 처리", notes = "")
    @PostMapping("/add")
    public Map<String, String> postAdd(@RequestBody OrderReqDTO orderReqDTO, Principal principal){

        User user = userService.findUser(principal.getName());

        orderService.createOrder(user, orderReqDTO);

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("result", "success");

        return resultMap;
    }

    @ApiOperation(value = "상품 구매 취소", notes = "")
    @PostMapping("/cancel/{orderId}")
    public Map<String, String> PostCancel(@PathVariable("orderId") Long orderId){

        orderService.cancelOrder(orderId);

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("result", "success");
        return resultMap;
    }

    @ApiOperation(value = "바로 주문 내역 임시 저장", notes = "즉시 주문하기 실행")
    @PostMapping("/addOrderTemporary")
    public Map<String, String> postAddOrderTemporary(@Valid @RequestBody ItemBuyReqDTO itemBuyReqDTO,
                                                     Principal principal){

        User user = userService.findUser(principal.getName());

        Long orderTemporaryId = orderService.addOrderTemporary(itemBuyReqDTO, user);

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("result", "success");
        resultMap.put("orderTemporaryId", orderTemporaryId.toString());

        return resultMap;
    }



}
