package com.carshop.mycarshop.service.cart;

import com.carshop.mycarshop.common.exception.NotExistDataByIDException;
import com.carshop.mycarshop.domain.cart.Cart;
import com.carshop.mycarshop.domain.cart.CartRepository;
import com.carshop.mycarshop.domain.notification.EventNotification;
import com.carshop.mycarshop.domain.notification.EventType;
import com.carshop.mycarshop.domain.shop.ShopItem;
import com.carshop.mycarshop.domain.shop.ShopItemRepository;
import com.carshop.mycarshop.domain.user.User;
import com.carshop.mycarshop.dto.cart.CartDetailResDTO;
import com.carshop.mycarshop.dto.shop.ItemBuyReqDTO;
import com.carshop.mycarshop.service.common.CommonUtils;
import com.carshop.mycarshop.service.notification.NotificationService;
import com.carshop.mycarshop.service.shop.ItemOptionService;
import com.carshop.mycarshop.service.shop.ShopItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    private final ItemOptionService itemOptionService;
    private final ShopItemService shopItemService;
    private final NotificationService notificationService;

    @Override
    public List<CartDetailResDTO> getCartAll(User user) {

        // 이벤트 체크
        final EventNotification event = notificationService.getNowDoingEventInfo(EventType.EVENT_BUY_ITEM_DISCOUNT);

        // 장바구니 리스트 get
        List<Cart> listCart = cartRepository.findByUser(user);

        return listCart.stream()
                .filter(Cart::getIsActive)  // 유효한 정보 만..
                .map(cart -> {
                    CartDetailResDTO cartDTO = entityToDTO(user, cart, event);
                    // 아이템 옵션 set
                    cartDTO.getListItemOption().addAll(itemOptionService.getListItemOptionInfo(cart.getListOptionId()));

                    // 아이템 이미지 파일 정보 매핑 ( 대표 이미지 만 )
                    cart.getShopItem().getItemImageSet()
                            .stream().filter(image -> image.getImageOrder() == 0)
                            .peek(log::error)
                            .forEach(image -> {
                                cartDTO.addImage(image.getItemImageId(), image.getUuid(), image.getFileName(),
                                        image.getImageOrder(), image.getIsMainImage());
                            });

                    return cartDTO;
                }).collect(Collectors.toList());
    }
    @Override
    public void addCart(ItemBuyReqDTO itemBuyReqDTO, User user) {

        ShopItem shopItem = shopItemService.getShopItemByItemName(itemBuyReqDTO.getItemName());

        // 이벤트 체크
        EventNotification event = notificationService.getNowDoingEventInfo(EventType.EVENT_BUY_ITEM_DISCOUNT);

        // 회원 등급, 이벤트 여부에 따라 아이템 가격 계산
        int discountPrice = CommonUtils.calcDiscountPrice(user, shopItem, event);

        Cart cart = Cart.builder()
                .shopItem(shopItem)
                .itemCount(itemBuyReqDTO.getItemCount())
                .discountPrice(discountPrice)
                .user(user)
                .isActive(true)
                .metricWeight(10)   // 학습용
                // 아이템 옵션 set
                .itemOptionId1(itemBuyReqDTO.getOptionId(0))
                .itemOptionId2(itemBuyReqDTO.getOptionId(1))
                .build();

        cartRepository.save(cart);
    }
    @Override
    public void deleteInCart(Long cartId) {

        Cart cart = getCartByCartId(cartId);

        cartRepository.delete(cart);
    }
    private static CartDetailResDTO entityToDTO(User user, Cart cart, EventNotification event) {

        return CartDetailResDTO.builder()
                .cartId(cart.getCartId())
                .shopItemId(cart.getShopItem().getShopItemId())
                .itemName(cart.getShopItem().getItemName())
                .itemTitle(cart.getShopItem().getItemTitle())
                .itemCount(cart.getItemCount())
                .itemPrice(cart.getShopItem().getItemPrice().getOriginalPrice())
                .discountPrice(CommonUtils.calcDiscountPrice(user, cart.getShopItem(), event))
                .isFreeDelivery(cart.getShopItem().isFreeDelivery())
                .build();
    }

    public Cart getCartByCartId(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> {
                    log.info("User expected to modify cart but was empty. cartId = '{}',"
                            , cartId);
                    return new NotExistDataByIDException("장바구니 정보가 존재하지 않습니다");
                });
    }
}