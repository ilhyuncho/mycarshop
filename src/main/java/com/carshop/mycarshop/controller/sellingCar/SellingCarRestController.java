package com.carshop.mycarshop.controller.sellingCar;

import com.carshop.mycarshop.domain.user.User;
import com.carshop.mycarshop.dto.PageRequestExtDTO;
import com.carshop.mycarshop.dto.PageResponseDTO;
import com.carshop.mycarshop.dto.sellingCar.SellingCarRegDTO;
import com.carshop.mycarshop.dto.sellingCar.SellingCarResDTO;
import com.carshop.mycarshop.service.sellingCar.SellingCarService;
import com.carshop.mycarshop.service.user.UserSearchCarHistoryService;
import com.carshop.mycarshop.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sellingCar")
@RequiredArgsConstructor
@Log4j2
public class SellingCarRestController {

    private final SellingCarService sellingCarService;
    private final UserService userService;
    private final UserSearchCarHistoryService userSearchCarHistoryService;

    @ApiOperation(value = "판매 차량 등록", notes = "차 소유주가 차량 등록")
    @PostMapping("/register")
    public Map<String,String> postRegisterSellingCar(@Valid @RequestBody SellingCarRegDTO sellingCarRegDTO,
                                                     BindingResult bindingResult,
                                                     Principal principal ){

        User user = userService.findUser(principal.getName());

        sellingCarService.registerSellingCar(user, sellingCarRegDTO);

        return new HashMap<>();
    }

    @ApiOperation(value = "판매 현황", notes = "차 소유주가 [판매 현황] 버튼 클릭")
    @GetMapping("/get")
    public SellingCarResDTO getSellingCar(Long sellingCarId, Principal principal){

        User user = userService.findUser(principal.getName());

        return sellingCarService.getSellingCarData(user, sellingCarId);
    }

    @ApiOperation(value = "판매 취소 or 완료 처리", notes = "판매 중이던 차량")
    @PostMapping("/modify")
    public Map<String,String> postModifySellingCar(@Valid @RequestBody SellingCarRegDTO sellingCarRegDTO,
                                                   BindingResult bindingResult,
                                                   Principal principal){

        userService.findUser(principal.getName());

        sellingCarService.updateSellingCar(sellingCarRegDTO);

        return new HashMap<>();
    }

    @ApiOperation(value = "판매 차량 리스트 정보 전달", notes = "판매 차량 리스트 화면")
    @GetMapping("/listSearch")
    public PageResponseDTO<SellingCarResDTO> getListSellingCar(PageRequestExtDTO pageRequestExtDT){
        return sellingCarService.getListSellingCar(pageRequestExtDT);
    }

    @ApiOperation(value = "추천 차량 정보 전달", notes = "메인 화면, 최대 5개 표시")
    @GetMapping("/recommend")
    public List<SellingCarResDTO> getRecommendSellingCar(String memberName){

        return sellingCarService.getListRecommend(memberName);
    }

    @ApiOperation(value = "최근 본 차량 정보 전달", notes = "메인 화면")
    @GetMapping("/recentlySeenCar")
    public List<SellingCarResDTO> getRecentlySeenCar(Principal principal){

        User user = userService.findUser(principal.getName());

        return userSearchCarHistoryService.getSearchCarHistory(user);
    }

    @ApiOperation(value = "판매 차량 좋아요", notes = "")
    @PostMapping("/like")
    public Map<String,String> postlike(@Valid @RequestBody SellingCarRegDTO sellingCarRegDTO,
                                       BindingResult bindingResult,
                                       Principal principal){

        User user = userService.findUser(principal.getName());

        sellingCarService.likeSellingCar(user, sellingCarRegDTO);

        return new HashMap<>();
    }
}
