package com.carshop.mycarshop.service.shop;

import com.carshop.mycarshop.common.exception.ItemNotFoundException;
import com.carshop.mycarshop.domain.notification.EventNotification;
import com.carshop.mycarshop.domain.notification.EventType;
import com.carshop.mycarshop.domain.shop.*;
import com.carshop.mycarshop.domain.user.User;
import com.carshop.mycarshop.dto.ImageDTO;
import com.carshop.mycarshop.dto.ImageOrderReqDTO;
import com.carshop.mycarshop.dto.shop.ItemOptionDTO;
import com.carshop.mycarshop.dto.shop.ShopItemExtandDTO;
import com.carshop.mycarshop.dto.shop.ShopItemReqDTO;
import com.carshop.mycarshop.dto.shop.ShopItemResDTO;
import com.carshop.mycarshop.service.common.CommonUtils;
import com.carshop.mycarshop.service.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
//@Scope(BeanDefinition.SCOPE_PROTOTYPE)  // 프로토타입 스코프 빈 생성 ( 변경 가능한 속성을 포함 시킬수 있다 )
//서버가 요청에 따라 독립적으로 오브젝트를 생성해서 [상태를 저장]해둬야 하는 경우
public class ShopItemServiceImpl implements ShopItemService {

    private final ShopItemRepository shopItemRepository;
    private final ItemPriceRepository itemPriceRepository;
    private final ItemImageRepository itemImageRepository;
    private final NotificationService notificationService;

    @Override
    public ShopItem getShopItemByItemName(String ItemName){
        return shopItemRepository.findByItemName(ItemName)
                .orElseThrow(() -> new ItemNotFoundException("해당 ItemName의 상품이 존재하지 않습니다"));
    }
    @Override
    public ShopItem getShopItemById(Long id){
        return shopItemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("해당 id의 상품이 존재하지않습니다"));
    }

    @Override
    public ShopItemExtandDTO getItemInfo(Long shopItemId, User user) {  // User는 null 이 올수 있음

        ShopItem shopItem = getShopItemById(shopItemId);

        // 진행 중인 이벤트 체크
        EventNotification event = notificationService.getNowDoingEventInfo(EventType.EVENT_BUY_ITEM_DISCOUNT);

        ShopItemExtandDTO shopItemExtandDTO = convertShopItemExtandDTO(shopItem);

        // 회원 등급, 이벤트 여부에 따라 아이템 가격 계산
        int discountPrice = CommonUtils.calcDiscountPrice(user, shopItem, event);
        shopItemExtandDTO.setDiscountPrice(discountPrice);

        return shopItemExtandDTO;
    }

    @Override
    public List<ShopItemExtandDTO> getAllItems() {    // 관리자 페이지 상품 리스트

        List<ShopItem> listShopItem = shopItemRepository.findAll();

        return listShopItem.stream()
                .map(ShopItemServiceImpl::convertShopItemExtandDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShopItemResDTO> getAllItemsForShop() {   // 악세서리 샵 상품 리스트 (심플)

        List<ShopItem> listShopItem = shopItemRepository.findAll();

        return listShopItem.stream()
                .map(ShopItemServiceImpl::convertShopItemDTO)
                .collect(Collectors.toList());
    }
    @Override
    public Long registerItem(ShopItemReqDTO shopItemReqDTO) {

        shopItemRepository.findByItemName(shopItemReqDTO.getItemName())
                .ifPresent(m -> {
                    throw new ItemNotFoundException("해당 상품 정보가 이미 존재 함");
                });

        // 1. 아이템 가격 정책 저장
        ItemPrice itemPrice = ItemPrice.builder()
                .originalPrice(shopItemReqDTO.getOriginalPrice())
                .membershipPercent(shopItemReqDTO.getMembershipPercent())
                .isEventTarget(shopItemReqDTO.getIsEventTarget())
                .build();

        itemPriceRepository.save(itemPrice);

        // 2. ShopItem 정보 저장
        ShopItem shopItem = dtoToEntity(shopItemReqDTO, itemPrice);
        // 아이템 옵션 set
        setItemOption(shopItemReqDTO, shopItem);

        return shopItemRepository.save(shopItem).getShopItemId();
    }

    private static void setItemOption(ShopItemReqDTO shopItemReqDTO, ShopItem shopItem) {

        if(shopItemReqDTO.getItemOptionList().isEmpty()){
            return;
        }

        shopItemReqDTO.getItemOptionList()
            .forEach(itemOptionDTO -> {
                String[] values = itemOptionDTO.getOptionValue().split(",");

                IntStream.range(0, values.length)
                        .forEach(orderIndex -> shopItem.setItemOption(itemOptionDTO, shopItem, orderIndex, values[orderIndex]));
            });
    }

    @Override
    public void modifyItem(ShopItemReqDTO shopItemReqDTO) {

        ShopItem shopItem = getShopItemById(shopItemReqDTO.getShopItemId());

        // 가격 정책 update
        shopItem.getItemPrice().changePriceInfo(shopItemReqDTO.getOriginalPrice(),
                shopItemReqDTO.getMembershipPercent(), shopItemReqDTO.getIsEventTarget());

        // shopItem 정보 update
        shopItem.updateInfo(shopItemReqDTO.getItemName(), shopItemReqDTO.getStockCount());

        // 첨부파일 update
        shopItem.updateImages(shopItemReqDTO.getFileNames(), shopItemReqDTO.getMainImageFileName());

        // 아이템 옵션 update
        shopItem.updateItemOption(shopItemReqDTO.getItemOptionList());
    }

    @Override
    public void modifyImageOrder(ImageOrderReqDTO imageOrderReqDTO) {

        ShopItem shopItem = getShopItemById(imageOrderReqDTO.getObjectId());

        // 관리자가 재설정한 image order
        Map<Long, Integer> mapImageDTO = imageOrderReqDTO.getImageOrderList().stream()
                .collect(Collectors.toMap(ImageDTO::getImageId, ImageDTO::getImageOrder));

        // DB에 등록된 image order 데이터 get
        List<ItemImage> listItemImage = itemImageRepository.findByShopItem(shopItem);

        listItemImage.stream()
                .filter(itemImage -> !itemImage.getIsMainImage()) // mainImage는 제외
                .forEach(itemImage -> {
                    itemImage.changeImageOrder(mapImageDTO.get(itemImage.getItemImageId()));
                });
    }

    @Override
    public void deleteItem(Long itemId) {
        shopItemRepository.deleteById(itemId);
    }

    private static ShopItemResDTO convertShopItemDTO(ShopItem shopItem) {

        // 1. sample DTO 셋팅
        ShopItemResDTO shopItemResDTO = entityToDTO(shopItem);
        // 2. Item Image 셋팅
        setItemImage(shopItem, shopItemResDTO, true);

        return shopItemResDTO;
    }
    private static ShopItemExtandDTO convertShopItemExtandDTO(ShopItem shopItem) {

        // 1. sample DTO 셋팅
        ShopItemResDTO shopItemResDTO = entityToDTO(shopItem);

        // 2. 확장 DTO 셋팅
        ShopItemExtandDTO shopItemExtandDTO = (ShopItemExtandDTO) shopItemResDTO;

        shopItemExtandDTO.setStockCount(shopItem.getStockCount());
        shopItemExtandDTO.setMembershipPercent(shopItem.getItemPrice().getMembershipPercent());
        shopItemExtandDTO.setIsEventTarget(shopItem.getItemPrice().getIsEventTarget());

        // 3. Item Image 셋팅
        setItemImage(shopItem, shopItemResDTO, true);

        // 4. ItemOption Map 셋팅
        setItemOption(shopItem, shopItemExtandDTO);

        return shopItemExtandDTO;
    }

    private static void setItemImage(ShopItem shopItem, ShopItemResDTO shopItemResDTO, boolean isOnlyMainImage) {

        if(shopItem.getItemImageSet().isEmpty()) {
            return;
        }

        shopItem.getItemImageSet().stream()
            .filter(itemImage -> !isOnlyMainImage || itemImage.getIsMainImage())
            .forEach(itemImage -> {
                shopItemResDTO.addImage(itemImage.getItemImageId(), itemImage.getUuid(), itemImage.getFileName(),
                        itemImage.getImageOrder(), itemImage.getIsMainImage());
            });
    }

    private static void setItemOption(ShopItem shopItem, ShopItemExtandDTO shopItemExtandDTO) {
        SortedMap<ItemOptionType, String> mapItemOption = shopItem.getMapItemOption();
        Map<ItemOptionType, String> mapItemOptionForView = shopItem.getMapItemOptionForView();

        mapItemOption.forEach((itemOptionType, optionValue) -> {
            shopItemExtandDTO.getListOptionType().add(
                    ItemOptionDTO.builder()
                            .optionType(itemOptionType.getName())
                            //.optionValue("0-선택안함, " + mapItemOption.get(itemOptionType))
                            .optionValue(optionValue)
                            .optionValueForView(mapItemOptionForView.get(itemOptionType))
                            .build()
            );
        });
    }

    private static ShopItemResDTO entityToDTO(ShopItem shopItem){

        return ShopItemExtandDTO.builder()
                .shopItemId(shopItem.getShopItemId())
                .itemName(shopItem.getItemName())
                .itemTitle(shopItem.getItemTitle())
                .itemDesc(shopItem.getItemDesc())
                .originalPrice(shopItem.getItemPrice().getOriginalPrice())
                .isFreeDelivery(shopItem.isFreeDelivery())
                .stockCount(shopItem.getStockCount())
                .purchaseCount(shopItem.getPurchaseCount())
                .build();
    }

    private static ShopItem dtoToEntity(ShopItemReqDTO shopItemReqDTO, ItemPrice itemPrice) {

        ShopItem shopItem = ShopItem.builder()
                .itemName(shopItemReqDTO.getItemName())
                .itemTitle(shopItemReqDTO.getItemTitle())
                .itemDesc(shopItemReqDTO.getItemDesc())
                .itemPrice(itemPrice)
                .stockCount(shopItemReqDTO.getStockCount())
                .purchaseCount(0)
                .build();

        if(shopItemReqDTO.getFileNames() != null){
            shopItemReqDTO.getFileNames().forEach(fileName ->{

                String[] arr = fileName.split("_");
                shopItem.addImage(arr[0], arr[1], arr[1].equals(shopItemReqDTO.getMainImageFileName()));
            });
        }
        return shopItem;
    }

}
