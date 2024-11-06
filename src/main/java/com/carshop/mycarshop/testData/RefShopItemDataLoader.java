package com.carshop.mycarshop.testData;

import com.carshop.mycarshop.domain.shop.*;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
@Log4j2
@Profile({"aws","test"})    // 이 클래스는 프로파일이 활성화될 때만 로드 된다.
@AllArgsConstructor
public class RefShopItemDataLoader {

    private final ItemPriceRepository itemPriceRepository;
    private final ShopItemRepository shopItemRepository;
    private final ItemImageRepository itemImageRepository;


    @EventListener(ApplicationReadyEvent.class) // 애플리케이션 시작 단계가 완료되면 발생한다.
    public void loadRefShopItemData() {

        if(shopItemRepository.count() == 0){
            // ShopItem 생성
            IntStream.rangeClosed(1,2).forEach(i -> {

                ItemPrice itemPrice = ItemPrice.builder()
                        .originalPrice(10000 * i)
                        .isEventTarget(true)
                        .membershipPercent(10)
                        .build();

                itemPriceRepository.save(itemPrice);

                ShopItem shopItem = ShopItem.builder()
                        .itemName("item" + i)
                        .itemPrice(itemPrice)
                        .stockCount(10000)
                        .purchaseCount(0)
                        .isFreeDelivery(i == 1)
                        .itemTitle("item" + i +"_title")
                        .itemDesc("item" + i +"Desc ~~~~~~~~~~~~!@1231242343546")
                        .build();

//            shopItem.addImage("1a1a1a", "ionic5.png");
//            shopItem.addImage("2a2a2a", "ionic51.png");

                if( i == 1 ){
                    shopItem.addItemOption(ItemOption.builder()
                            .itemOptionType(ItemOptionType.OPTION_COLOR).typePriority(0).optionName("흰색").optionOrder(0).build());
                    shopItem.addItemOption(ItemOption.builder()
                            .itemOptionType(ItemOptionType.OPTION_COLOR).typePriority(0).optionName("파랑색").optionOrder(1).build());
                    shopItem.addItemOption(ItemOption.builder()
                            .itemOptionType(ItemOptionType.OPTION_COLOR).typePriority(0).optionName("검은색").optionOrder(2).build());
                    shopItem.addItemOption(ItemOption.builder()
                            .itemOptionType(ItemOptionType.OPTION_COLOR).typePriority(0).optionName("빨강색").optionOrder(3).build());

                    shopItem.addItemOption(ItemOption.builder()
                            .itemOptionType(ItemOptionType.OPTION_SIZE).typePriority(1).optionName("s 사이즈").optionOrder(0).build());
                    shopItem.addItemOption(ItemOption.builder()
                            .itemOptionType(ItemOptionType.OPTION_SIZE).typePriority(1).optionName("m 사이즈").optionOrder(1).build());
                    shopItem.addItemOption(ItemOption.builder()
                            .itemOptionType(ItemOptionType.OPTION_SIZE).typePriority(1).optionName("l 사이즈").optionOrder(2).build());
                    shopItem.addItemOption(ItemOption.builder()
                            .itemOptionType(ItemOptionType.OPTION_SIZE).typePriority(1).optionName("xl 사이즈").optionOrder(3).build());
                }
                else if( i == 2){
                    shopItem.addItemOption(ItemOption.builder()
                            .itemOptionType(ItemOptionType.OPTION_TYPE).typePriority(2).optionName("타입1").optionOrder(0).build());
                    shopItem.addItemOption(ItemOption.builder()
                            .itemOptionType(ItemOptionType.OPTION_TYPE).typePriority(2).optionName("타입2").optionOrder(1).build());
                    shopItem.addItemOption(ItemOption.builder()
                            .itemOptionType(ItemOptionType.OPTION_TYPE).typePriority(2).optionName("타입3").optionOrder(2).build());
                    shopItem.addItemOption(ItemOption.builder()
                            .itemOptionType(ItemOptionType.OPTION_TYPE).typePriority(2).optionName("타입4").optionOrder(3).build());
                }


                shopItemRepository.save(shopItem);

                ItemImage itemImage = ItemImage.builder()
                        .imageOrder(0)
                        .fileName("item" + i + ".png")
                        .uuid("1111")
                        .shopItem(shopItem)
                        .isMainImage(true)
                        .build();
                itemImageRepository.save(itemImage);
            });
        }
    }
}
