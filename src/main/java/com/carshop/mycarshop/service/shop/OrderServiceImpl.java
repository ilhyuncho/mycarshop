package com.carshop.mycarshop.service.shop;

import com.carshop.mycarshop.common.exception.InvalidUserPointException;
import com.carshop.mycarshop.common.exception.ItemNotFoundException;
import com.carshop.mycarshop.common.exception.orderNotFoundException;
import com.carshop.mycarshop.domain.cart.Cart;
import com.carshop.mycarshop.domain.notification.EventNotification;
import com.carshop.mycarshop.domain.notification.EventType;
import com.carshop.mycarshop.domain.review.Review;
import com.carshop.mycarshop.domain.review.ReviewRepository;
import com.carshop.mycarshop.domain.shop.*;
import com.carshop.mycarshop.domain.user.*;
import com.carshop.mycarshop.dto.ImageListDTO;
import com.carshop.mycarshop.dto.PageRequestDTO;
import com.carshop.mycarshop.dto.PageResponseDTO;
import com.carshop.mycarshop.dto.order.*;
import com.carshop.mycarshop.dto.shop.ItemBuyReqDTO;
import com.carshop.mycarshop.service.cart.CartService;
import com.carshop.mycarshop.service.common.CommonUtils;
import com.carshop.mycarshop.service.notification.NotificationService;
import com.carshop.mycarshop.service.user.UserPointHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserAddressBookRepository userAddressBookRepository;
    private final OrderTemporaryRepository orderTemporaryRepository;
    private final ReviewRepository reviewRepository;

    private final CartService cartService;
    private final ItemOptionService itemOptionService;
    private final NotificationService notificationService;
    private final UserPointHistoryService userPointHistoryService;
    private final ShopItemService shopItemService;

    @Override
    public Order getOrderInfo(Long orderId){
        return orderRepository.findById(orderId)
                .orElseThrow(() -> {
                    log.info("User expected to delete cart but was empty. orderId = '{}',", orderId);
                    return new orderNotFoundException("해당 주문 정보가 존재 하지 않습니다");
                });
    }
    @Override
    public PageResponseDTO<OrderResDTO> getOrderAll(User user, PageRequestDTO pageRequestDTO) {

        // 고객의 주문 내역 조회
        List<Order> listOrder = orderRepository.findByUser(user);

        // 주문 별 상품 구매 내역 조회
        Pageable pageable = pageRequestDTO.getPageable("orderItemId");
        Page<OrderItem> resultOrderItem = orderItemRepository.findByOrders(listOrder, pageable);
        List<OrderItem> listOrderItem = resultOrderItem.getContent();

        // 주문 별 상품 내역 셋팅
        List<OrderResDTO> listResDTO = setOrderItems(listOrderItem);;

        return PageResponseDTO.<OrderResDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(listResDTO)
                .total((int)resultOrderItem.getTotalElements()) // 수정 해야 함!!!
                .build();
    }

    @Override
    public List<OrderItemResDTO> getOrderDetail(User user, Long orderId) {

        Order order = getOrderInfo(orderId);

        List<OrderItem> listOrderItem = orderItemRepository.findByOrder(order);

        List<OrderItemResDTO> listOrderItemResDTO = listOrderItem.stream().map(orderItem -> {
            // 리뷰 작성 유무 확인
            Optional<Review> review = reviewRepository.findByOrderItemIdAndReviewer(orderItem.getOrderItemId(), user.getMember().getMemberId());

            OrderItemResDTO itemDTO = OrderItemResDTO.builder()
                    .orderId(orderItem.getOrder().getOrderId())
                    .orderItemId(orderItem.getOrderItemId())
                    .orderCount(orderItem.getOrderCount())
                    .shopItemId(orderItem.getShopItem().getShopItemId())
                    .itemName(orderItem.getShopItem().getItemName())
                    .itemTitle(orderItem.getShopItem().getItemTitle())
                    .orderPrice(orderItem.getOrderPrice())
                    .deliveryStatus(order.getDeliveryStatus())
                    .orderDate(order.getOrderTime().toLocalDate())
                    .isReviewWrite(review.isPresent())
                    .build();

            // 아이템 옵션 set
            itemDTO.getListItemOption().addAll(itemOptionService.getListItemOptionInfo(orderItem.getListOptionId()));
            // 아이템 대표 이미지 셋팅
            setMainImage(itemDTO, orderItem.getShopItem());

            return itemDTO;
        }).collect(Collectors.toList());

        listOrderItemResDTO.forEach(log::error);
        return listOrderItemResDTO;
    }

    @Override
    public OrderTemporaryResDTO getOrderTemporary(Long orderTemporaryId) {

        OrderTemporary orderTemporary = orderTemporaryRepository.findById(orderTemporaryId)
                .orElseThrow(() -> new ItemNotFoundException("OrderTemporary 이 존재하지않습니다"));

        // 주문 내역 유효 기간 체크
        LocalTime expiredTime = orderTemporary.getExpiredDate().toLocalTime();
        if(CommonUtils.checkTimeOver(expiredTime)){
            // throw new OrderExpiredException("주문서 만료 기간이 지났습니다");
            return null;
        }

        OrderTemporaryResDTO orderTemporaryResDTO = OrderTemporaryResDTO.builder()
                .orderTemporaryId(orderTemporary.getOrderTemporaryId())
                .shopItemId(orderTemporary.getShopItem().getShopItemId())
                .itemName(orderTemporary.getShopItem().getItemName())
                .orderCount(orderTemporary.getItemCount())
                .orderPrice(orderTemporary.getShopItem().getItemPrice().getOriginalPrice())
                .discountPrice(orderTemporary.getDiscountPrice())
                .orderDate(orderTemporary.getRegDate().toLocalDate())
                .build();

        // 아이템 옵션 set
        orderTemporaryResDTO.getListItemOption().addAll(itemOptionService.getListItemOptionInfo(orderTemporary.getListOptionId()));

        // 아이템 대표 이미지 셋팅
        setMainImage(orderTemporaryResDTO, orderTemporary.getShopItem());

        return orderTemporaryResDTO;
    }
    @Override
    public OrderDeliveryResDTO getOrderDeliveryProcess(Long orderId) {

        Order order = getOrderInfo(orderId);

        UserAddressBook userAddressBook = order.getUserAddressBook();

        return OrderDeliveryResDTO.builder()
                .orderId(order.getOrderId())
                .deliveryStatus(order.getDeliveryStatus().getName())
                .deliveryName(userAddressBook.getDeliveryName())
                .recipientName(userAddressBook.getRecipientName())
                .deliveryRequest(userAddressBook.getDeliveryRequest())
                .fullAddress(userAddressBook.getAddress().fullAddress())
                .recipientPhoneNumber(userAddressBook.getRecipientPhoneNumber())
                .build();
    }

    @Override
    public Long createOrder(User user, OrderReqDTO orderReqDTO){

        // 사용 포인트 검증
        validateUserPoint(user, orderReqDTO.getUseMPoint());

        // 고객 배송 정보
        UserAddressBook userAddressBook = userAddressBookRepository.findById(orderReqDTO.getUserAddressBookId())
                .orElseThrow(() -> new ItemNotFoundException("배송 주소 정보가 존재하지않습니다"));

        // 상세 구매 아이템 정보 생성
        List<OrderItem> listOrderItem = createOrderItems(orderReqDTO);

        // 포인트 사용 이력 저장
        saveUserPointHistory(user, orderReqDTO);

        // Order 생성
        Order order = Order.createOrder(user, userAddressBook, orderReqDTO, listOrderItem);
        return orderRepository.save(order).getOrderId();
    }

    @Override
    public void cancelOrder(Long orderId) {

        Order order = getOrderInfo(orderId);

        order.cancelOrder();

        // 상품 별 구매 수량 롤백
        List<OrderItem> orderItemList = order.getOrderItemList();
        orderItemList.forEach(orderItem-> {
            orderItem.getShopItem().minusPurchaseCount(orderItem.getOrderCount());
        });

        if(order.getUseMPoint() > 0){
            userPointHistoryService.saveUserPointHistory(order.getUser(), PointType.RETURN,
                    PointSituation.CANCEL_ITEM_RETURN_POINT, order.getUseMPoint(), null);
        }
    }

    @Override
    public Long addOrderTemporary(ItemBuyReqDTO itemBuyReqDTO, User user) {

        ShopItem shopItem = shopItemService.getShopItemByItemName(itemBuyReqDTO.getItemName());

        // 이벤트 체크
        EventNotification event = notificationService.getNowDoingEventInfo(EventType.EVENT_BUY_ITEM_DISCOUNT);

        // 회원 등급, 이벤트 여부에 따라 아이템 가격 계산
        int discountPrice = CommonUtils.calcDiscountPrice(user, shopItem, event);

        OrderTemporary orderTemporary = OrderTemporary.builder()
                .shopItem(shopItem)
                .itemCount(itemBuyReqDTO.getItemCount())
                .discountPrice(discountPrice)
                .user(user)
                // 아이템 옵션 set
                .itemOptionId1(itemBuyReqDTO.getOptionId(0))
                .itemOptionId2(itemBuyReqDTO.getOptionId(1))
                .build();

        return orderTemporaryRepository.save(orderTemporary).getOrderTemporaryId();
    }

    private static List<OrderResDTO> setOrderItems(List<OrderItem> listOrderItem) {

        return listOrderItem.stream()
                .collect(Collectors.groupingBy(OrderItem::getOrder))
                .entrySet().stream()
                .map(entry -> {
                    Order order = entry.getKey();
                    List<OrderItem> listItem = entry.getValue();

                    OrderResDTO orderResDTO = entityToDTO(order);
                    orderResDTO.setItemTitle(makeItemsName(listItem));

                    // 아이템 대표 이미지 셋팅
                    listItem.forEach(item -> setMainImage(orderResDTO, item.getShopItem()));

                    return orderResDTO;
                })
                .sorted(Comparator.comparing(OrderResDTO::getOrderId).reversed())       // 주문 순서 역순으로 정렬
                .collect(Collectors.toList());
    }

    private static String makeItemsName(List<OrderItem> orderItems) {
        if(orderItems.isEmpty()){
            return "";
        }
        return orderItems.stream()
                .map(orderItem -> orderItem.getShopItem().getItemTitle())
                .reduce("", (a, b) -> a + b + " ,")
                .replaceFirst(".$", "");    // 정규 표현식을 활용한 마지막 문자 제거
        // . -> 모든 문자, $ -> 문자열의 끝
    }

    private static <T extends ImageListDTO> void setMainImage(T target, ShopItem shopItem) {

        if(shopItem.getItemImageSet().isEmpty()){
            return;
        }

        shopItem.getItemImageSet().stream()
            .filter(ItemImage::getIsMainImage)
            .forEach(image -> target.addImage(image.getItemImageId(), image.getUuid(), image.getFileName(),
                    image.getImageOrder(), true));
    }

    private void validateUserPoint(User user, int useMPoint){

        if(useMPoint > 0 && useMPoint > user.getMPoint()){
            throw new InvalidUserPointException("사용 포인트 값이 잘못 되었습니다");
        }
    }

    private void saveUserPointHistory(User user, OrderReqDTO orderReqDTO) {

        if(orderReqDTO.getUseMPoint() > 0){
            userPointHistoryService.saveUserPointHistory(user, PointType.CONSUME,
                    PointSituation.BUY_ITEM_WITH_POINT, orderReqDTO.getUseMPoint() * -1, null);
        }
    }

    private List<OrderItem> createOrderItems(OrderReqDTO orderReqDTO) {

        return orderReqDTO.getListOrderDetail().stream().map(orderDetailDTO -> {
            // 장바구니를 통해서 주문시
            disableCartState(orderDetailDTO.getCartId());

            return createOrderItem(orderDetailDTO);
        }).collect(Collectors.toList());
    }

    private void disableCartState(Long cartId){

        if(cartId != null) {
            Cart cart = cartService.getCartByCartId(cartId);
            // 장바구니 정보 비활성화
            cart.changeIsActive(false);
        }
    }

    private OrderItem createOrderItem(OrderDetailDTO orderDetailDTO) {

        ShopItem shopItem = shopItemService.getShopItemById(orderDetailDTO.getItemId());

        return OrderItem.createOrderItem(orderDetailDTO, shopItem);
    }

    private static OrderResDTO entityToDTO(Order order) {

        return OrderResDTO.builder()
                .orderId(order.getOrderId())
                .deliveryStatus(order.getDeliveryStatus().getName())
                .orderDate(order.getOrderTime().toLocalDate())
                .orderPrice(order.getTotalPrice())
                .paymentPrice(order.getTotalPaymentPrice())
                .build();
    }

}
