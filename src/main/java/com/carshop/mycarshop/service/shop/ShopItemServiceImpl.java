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
    public ShopItemExtandDTO getItemInfo(Long shopItemId, User user) {  // User는 null 이 올수 있음

        ShopItem shopItem = shopItemRepository.findById(shopItemId)
                .orElseThrow(() -> new ItemNotFoundException("해당 상품 정보가 존재하지않습니다"));

        // 진행 중인 이벤트 체크
        EventNotification event = notificationService.getNowDoingEventInfo(EventType.EVENT_BUY_ITEM_DISCOUNT);
        // 회원 등급, 이벤트 여부에 따라 아이템 가격 계산
        int discountPrice = CommonUtils.calcDiscountPrice(user, shopItem, event);

        ShopItemExtandDTO shopItemExtandDTO = convertShopItemExtandDTO(shopItem);
        shopItemExtandDTO.setDiscountPrice(discountPrice);

        return shopItemExtandDTO;
    }

    @Override
    public List<ShopItemExtandDTO> getAllItems() {    // 관리자 페이지 상품 리스트

        List<ShopItem> listShopItem = shopItemRepository.findAll();

        List<ShopItemExtandDTO> listShopItemDTO = listShopItem.stream()
                .map(ShopItemServiceImpl::convertShopItemExtandDTO)
                .collect(Collectors.toList());

        // 대표 이미지만 필터링 ( ImageOrder = 0 )------------------begin---------------
//        for (ShopItemExtandDTO dto : listShopItemDTO) {
//            dto.getFileNames().stream()
//                    .filter(carImage -> carImage.getImageOrder() != 0)
//                    .collect(Collectors.toList())
//                    .forEach(x-> dto.getFileNames().remove(x));
//        }
        // 대표 이미지만 필터링 ( ImageOrder = 0 )------------------end---------------

        return listShopItemDTO;
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

        // 아이템 가격 정책 저장
        ItemPrice itemPrice = ItemPrice.builder()
                .originalPrice(shopItemReqDTO.getOriginalPrice())
                .membershipPercent(shopItemReqDTO.getMembershipPercent())
                .isEventTarget(shopItemReqDTO.getIsEventTarget())
                .build();

        itemPriceRepository.save(itemPrice);

        // ShopItem 정보 저장
        ShopItem shopItem = dtoToEntity(shopItemReqDTO, itemPrice);

        // 아이템 옵션 set
        shopItemReqDTO.getItemOptionList().forEach(itemOptionDTO -> {
            String[] values = itemOptionDTO.getOptionValue().split(",");
            int orderIndex = 0;
            for (String value : values) {

                ItemOption itemOption = ItemOption.builder()
                        .itemOptionType(ItemOptionType.fromValue(Integer.valueOf(itemOptionDTO.getOptionType())))
                        .optionOrder(orderIndex++)
                        .typePriority(itemOptionDTO.getTypePriority())
                        .optionName(value.trim())
                        .shopItem(shopItem)
                        .build();

                shopItem.addItemOption(itemOption);
            }
        });

        ShopItem saveItem = shopItemRepository.save(shopItem);

        return saveItem.getShopItemId();
    }

    @Override
    public void modifyItem(ShopItemReqDTO shopItemReqDTO) {

        ShopItem shopItem = shopItemRepository.findById(shopItemReqDTO.getShopItemId())
                .orElseThrow(() -> new ItemNotFoundException("해당 상품 정보가 존재하지않습니다"));

        // 가격 정책 update
        shopItem.getItemPrice().changePriceInfo(shopItemReqDTO.getOriginalPrice(),
                shopItemReqDTO.getMembershipPercent(), shopItemReqDTO.getIsEventTarget());

        // shopItem 정보 update
        shopItem.updateInfo(shopItemReqDTO.getItemName(), shopItemReqDTO.getStockCount());

        // 첨부파일 update
        shopItem.updateImages(shopItemReqDTO.getFileNames(), shopItemReqDTO.getMainImageFileName());

        // 아이템 옵션 udpate
        shopItem.updateItemOption(shopItemReqDTO.getItemOptionList());

        //shopItemRepository.save(shopItem);
    }

    @Override
    public void modifyImageOrder(ImageOrderReqDTO imageOrderReqDTO) {

        ShopItem shopItem = shopItemRepository.findById(imageOrderReqDTO.getObjectId())
                .orElseThrow(() -> new ItemNotFoundException("해당 상품이 존재하지않습니다"));

        // 관리자가 재설정한 image order
        Map<Long, Integer> mapImageDTO = imageOrderReqDTO.getImageOrderList().stream()
                .collect(Collectors.toMap(ImageDTO::getImageId, ImageDTO::getImageOrder));

        // DB에 등록된 image order 데이터 get
        List<ItemImage> listItemImage = itemImageRepository.findByShopItem(shopItem);

//        listItemImage.forEach(log::error);
//
//        for (Map.Entry<Long, Integer> stringListEntry : mapImageDTO.entrySet()) {
//            log.error("key:" + stringListEntry.getKey());
//            log.error("value:" + stringListEntry.getValue());
//        }

        listItemImage.stream().filter(itemImage -> !itemImage.getIsMainImage()) // mainImage는 제외
                .forEach(itemImage -> {
                    log.error(itemImage.getItemImageId());
                    itemImage.changeImageOrder(mapImageDTO.get(itemImage.getItemImageId()));
                });
    }

    @Override
    public void deleteItem(Long itemId) {
        shopItemRepository.deleteById(itemId);
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

    private static ShopItemResDTO entityToDTO(ShopItem shopItem){

        ShopItemExtandDTO shopItemExtandDTO = ShopItemExtandDTO.builder()
                .shopItemId(shopItem.getShopItemId())
                .itemName(shopItem.getItemName())
                .itemTitle(shopItem.getItemTitle())
                .itemDesc(shopItem.getItemDesc())
                .originalPrice(shopItem.getItemPrice().getOriginalPrice())
                .isFreeDelivery(shopItem.isFreeDelivery())
                .stockCount(shopItem.getStockCount())
                .purchaseCount(shopItem.getPurchaseCount())
                .build();

        if( shopItem.getItemImageSet().size() > 0) {
            shopItem.getItemImageSet().forEach(shopItemImage -> {
                shopItemExtandDTO.addImage(shopItemImage.getItemImageId(), shopItemImage.getUuid(), shopItemImage.getFileName(),
                        shopItemImage.getImageOrder(), shopItemImage.getIsMainImage());
            });
        }

        return shopItemExtandDTO;
    }

    private static ShopItemResDTO convertShopItemDTO(ShopItem shopItem) {

        return entityToDTO(shopItem);   // 1. sample DTO 셋팅
    }
    private static ShopItemExtandDTO convertShopItemExtandDTO(ShopItem shopItem) {

        // 1. sample DTO 셋팅
        ShopItemResDTO shopItemResDTO = entityToDTO(shopItem);

        // 2. 확장 DTO 셋팅
        ShopItemExtandDTO shopItemExtandDTO = (ShopItemExtandDTO) shopItemResDTO;

        shopItemExtandDTO.setStockCount(shopItem.getStockCount());
        shopItemExtandDTO.setMembershipPercent(shopItem.getItemPrice().getMembershipPercent());
        shopItemExtandDTO.setIsEventTarget(shopItem.getItemPrice().getIsEventTarget());

        ////////////////////////////////////////////////////////////
        // ItemOption Map 정보 가져오기
        SortedMap<ItemOptionType, String> mapItemOption = shopItem.getMapItemOption();
        Map<ItemOptionType, String> mapItemOptionForView = shopItem.getMapItemOptionForView();

        for (ItemOptionType itemOptionType : mapItemOption.keySet()) {
            shopItemExtandDTO.getListOptionType().add(
                    ItemOptionDTO.builder()
                            .optionType(itemOptionType.getName())
                            .optionValue("0-선택안함, " + mapItemOption.get(itemOptionType))
                            .optionValueForView(mapItemOptionForView.get(itemOptionType))
                            .build()
            );
        }

        return shopItemExtandDTO;
    }

}
