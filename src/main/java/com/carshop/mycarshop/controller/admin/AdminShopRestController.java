package com.carshop.mycarshop.controller.admin;

import com.carshop.mycarshop.dto.ImageOrderReqDTO;
import com.carshop.mycarshop.dto.shop.ShopItemReqDTO;
import com.carshop.mycarshop.service.shop.ShopItemService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Log4j2
public class AdminShopRestController {

    private final ShopItemService shopItemService;

    @ApiOperation(value = "[상품] 데이터 넣기", notes = "관리자 접근")
    @PostMapping("/registerShopItem")
    public ResponseEntity<Map<String, String>> postRegisterShopItem(@Valid @RequestBody ShopItemReqDTO shopItemReqDTO,
                                                                    BindingResult bindingResult) throws BindException {

        if(bindingResult.hasErrors()) {
            log.error("bindingResult.hasErrors()~~~");
            throw new BindException(bindingResult);
        }

        Long ItemId = shopItemService.registerItem(shopItemReqDTO);

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("result", "success");
        resultMap.put("ItemId", ItemId.toString());

        return ResponseEntity.status(HttpStatus.OK).body(resultMap);
    }

    @ApiOperation(value = "[상품] 데이터 수정", notes = "관리자 접근")
    @PostMapping("/modifyShopItem")
    public ResponseEntity<Map<String, String>> postShopItemModify(@Valid @RequestBody ShopItemReqDTO shopItemReqDTO,
                                                                  BindingResult bindingResult) throws BindException {

        if(bindingResult.hasErrors()) {
            log.error("bindingResult.hasErrors()~~~");
            throw new BindException(bindingResult);
        }

        shopItemService.modifyItem(shopItemReqDTO);

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("result", "success");

        return ResponseEntity.status(HttpStatus.OK).body(resultMap);
    }
    @ApiOperation(value = "[상품] 이미지 순서 변경", notes = "관리자 접근")
    @PostMapping("/modifyImageOrder")
    public ResponseEntity<Map<String, String>> postImageOrderModify(@Valid @RequestBody ImageOrderReqDTO imageOrderReqDTO,
                                                                    BindingResult bindingResult) throws BindException {

        if(bindingResult.hasErrors()) {
            log.error("bindingResult.hasErrors()~~~");
            throw new BindException(bindingResult);
        }

        shopItemService.modifyImageOrder(imageOrderReqDTO);

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("result", "success");
        return ResponseEntity.status(HttpStatus.OK).body(resultMap);
    }


}
