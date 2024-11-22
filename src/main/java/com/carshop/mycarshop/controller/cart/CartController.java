package com.carshop.mycarshop.controller.cart;

import com.carshop.mycarshop.config.ConfigProperties;
import com.carshop.mycarshop.domain.user.User;
import com.carshop.mycarshop.dto.PageRequestDTO;
import com.carshop.mycarshop.dto.cart.CartDetailResDTO;
import com.carshop.mycarshop.dto.user.UserAddressBookResDTO;
import com.carshop.mycarshop.service.cart.CartService;
import com.carshop.mycarshop.service.user.UserAddressBookService;
import com.carshop.mycarshop.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
@Log4j2
@PreAuthorize("hasRole('USER')")
public class CartController {
    private final CartService cartService;
    private final UserService userService;
    private final UserAddressBookService userAddressBookService;

    private final ConfigProperties configProperties;

    @ApiOperation(value = "장바구니 조회", notes = "장바구니에 있는 모든 상품을 조회")
    @GetMapping("/list")
    public String getCart(@ModelAttribute("pageRequestDto") PageRequestDTO pageRequestDTO,
                          Model model, Principal principal){

        // test start
        log.error("ConfigProperties test value - hostName : " + configProperties.getHostName());
        log.error("ConfigProperties test value - hostName : " + configProperties.getSendName());
        log.error("ConfigProperties test value - hostName : " + configProperties.getPort());

        // 속성 변경이 가능
        configProperties.setPort(8090);

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        log.error("authentication.getName() : " + authentication.getName());
        log.error("authentication.getPrincipal() : " + authentication.getPrincipal());
        log.error("Principal : " + principal);

        // test end


        User user = userService.findUser(principal.getName());

        List<CartDetailResDTO> listDto = cartService.getCartAll(user);

        UserAddressBookResDTO mainAddressInfo = userAddressBookService.getMainAddressInfo(user);

        model.addAttribute("responseDTO", listDto);
        model.addAttribute("mainAddressInfo", mainAddressInfo);
        model.addAttribute("mPoint", user.getMPoint());

        return "cart/cartList";
    }

}
