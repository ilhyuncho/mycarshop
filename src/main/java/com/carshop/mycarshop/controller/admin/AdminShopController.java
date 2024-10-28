package com.carshop.mycarshop.controller.admin;

import com.carshop.mycarshop.common.aspect.customArgumentLogging.ElapseLoggable;
import com.carshop.mycarshop.common.handler.FileHandler;
import com.carshop.mycarshop.domain.shop.ItemOptionType;
import com.carshop.mycarshop.dto.shop.ShopItemExtandDTO;
import com.carshop.mycarshop.dto.shop.ShopItemReqDTO;
import com.carshop.mycarshop.service.shop.ShopItemService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Log4j2
public class AdminShopController {

    private final ShopItemService shopItemService;
    private final FileHandler fileHandler;

    @GetMapping("/shopItem")

    @ElapseLoggable // 커스텀 aspect 지정
    public String getShopItem(Model model){

        // 프로토타입 스코프 빈 테스트용 ( shopItemService 가 각각 생성되어 전달 됨
        log.error(shopItemService);

        List<ShopItemExtandDTO> listDTO = shopItemService.getAllItems();       // 관리자 페이지 메인

        model.addAttribute("itemList", listDTO);
        model.addAttribute("ItemOptionTypeList", ItemOptionType.getAllTypes());

        listDTO.forEach(log::error);

        return "admin/shopItem";
    }

    @ApiOperation(value = "관리자 페이지에서 아이템 상세 정보 or 수정", notes = "[관리자] 아이템 수정")
    @GetMapping({"/shopItemDetail/{shopItemId}",
            "/shopItemModify/{shopItemId}"})
    public String shopItemDetailOrModify(HttpServletRequest request,
                                         @PathVariable("shopItemId") Long shopItemId, Model model){

        ShopItemExtandDTO shopItem = shopItemService.getItemInfo(shopItemId, null);

        model.addAttribute("responseDTO", shopItem);
        model.addAttribute("ItemOptionTypeList", ItemOptionType.getAllTypes());

        log.error(shopItem);

        String requestURI = request.getRequestURI();
        return request.getRequestURI().substring(0, requestURI.lastIndexOf("/"));
    }

    @PostMapping("/shopItemDelete")
    public String postShopItemDelete(ShopItemReqDTO shopItemReqDTO,
                                     RedirectAttributes redirectAttributes){

        shopItemService.deleteItem(shopItemReqDTO.getShopItemId());

        // Item이 db에서 삭제되었다면 첨부파일 삭제
        List<String> fileNames = shopItemReqDTO.getFileNames();
        if(fileNames != null && fileNames.size() > 0){
            fileHandler.removeFiles(fileNames);
        }

        redirectAttributes.addFlashAttribute("result", "removed");

        return "redirect:admin/shopItem";
    }


    @GetMapping("/imageOrderModify/{shopItemId}")
    public String getImageOrderModify(@PathVariable("shopItemId") Long shopItemId, Model model){

        ShopItemExtandDTO shopItem = shopItemService.getItemInfo(shopItemId, null);

        model.addAttribute("responseDTO", shopItem);

        return "admin/imageOrderModify";
    }


}
