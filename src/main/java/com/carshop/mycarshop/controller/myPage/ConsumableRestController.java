package com.carshop.mycarshop.controller.myPage;

import com.carshop.mycarshop.domain.user.User;
import com.carshop.mycarshop.dto.car.CarConsumableRegDTO;
import com.carshop.mycarshop.service.carConsumable.CarConsumableService;
import com.carshop.mycarshop.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/consumable")
@RequiredArgsConstructor
@Log4j2
@PreAuthorize("hasRole('USER')")
public class ConsumableRestController {

    private final CarConsumableService carConsumableService;
    private final UserService userService;

    @ApiOperation(value = "소모품 교환 날짜 등록", notes = "차 소유주가 등록")
    @PostMapping("/register")
    public Map<String,String> postRegisterConsumable(@Validated @RequestBody CarConsumableRegDTO carConsumableRegDTO,
                                                     BindingResult bindingResult,
                                                     Principal principal ){

        userService.findUser(principal.getName());

        carConsumableService.registerConsumable(carConsumableRegDTO);

        return new HashMap<>();
    }

    @ApiOperation(value = "소모품 내역 정보 수정", notes = "차 소유주가 등록")
    @PostMapping("/modify")
    public Map<String,String> postModifyConsumable(@Valid @RequestBody CarConsumableRegDTO carConsumableRegDTO,
                                                   BindingResult bindingResult,
                                                   Principal principal){

        userService.findUser(principal.getName());

        carConsumableService.modifyConsumable(carConsumableRegDTO);

        return new HashMap<>();
    }

    @ApiOperation(value = "소모품 내역 정보 삭제", notes = "차 소유주가 등록")
    @PostMapping("/remove/{consumableId}")
    public Map<String,String> postRemoveConsumable(@PathVariable Long consumableId,
                                                   Principal principal ) {

        userService.findUser(principal.getName());

        carConsumableService.removeConsumable(consumableId);

        return new HashMap<>();
    }

}
