package com.carshop.mycarshop.controller.buyingCar;

import com.carshop.mycarshop.common.exception.UserNotFoundException;
import com.carshop.mycarshop.common.message.MessageCode;
import com.carshop.mycarshop.common.message.MessageHandler;
import com.carshop.mycarshop.domain.buyingCar.BuyCarStatus;
import com.carshop.mycarshop.domain.car.Car;
import com.carshop.mycarshop.domain.user.User;
import com.carshop.mycarshop.dto.PageRequestDTO;
import com.carshop.mycarshop.dto.buyingCar.BuyingCarListResDTO;
import com.carshop.mycarshop.dto.buyingCar.BuyingCarRegDTO;
import com.carshop.mycarshop.dto.buyingCar.BuyingCarViewDTO;
import com.carshop.mycarshop.service.buyingCar.BuyingCarService;
import com.carshop.mycarshop.service.car.CarService;
import com.carshop.mycarshop.service.user.UserAlarmService;
import com.carshop.mycarshop.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyingCar")
@RequiredArgsConstructor
@Log4j2
public class BuyingCarRestController {

    private final BuyingCarService buyingCarService;
    private final UserAlarmService userAlarmService;
    private final UserService userService;
    private final CarService carService;

    private final MessageHandler messageHandler;

    @ApiOperation(value = "차량 구매 제안 or 취소", notes = "")
    @PostMapping("/offer")
    public Map<String,String> postOffer(@Valid @RequestBody BuyingCarRegDTO buyingCarRegDTO,
                                        BindingResult bindingResult,
                                        Principal principal ) throws BindException {
        if(bindingResult.hasErrors()){
            log.error("has errors.....");
            throw new BindException(bindingResult);
        }

        User offerUser = userService.findUser(principal.getName());

        Car car = carService.getCarInfo(buyingCarRegDTO.getCarId());
        User carOwnerUser = car.getUser();
        if(carOwnerUser == null) {
            throw new UserNotFoundException("해당 유저는 존재하지 않습니다");
        }

        // 신청 상태 get
        BuyCarStatus buyCarStatus = BuyCarStatus.fromValue(buyingCarRegDTO.getOfferType());

        if(buyCarStatus.equals(BuyCarStatus.AUCTION_REQUEST) ||
                buyCarStatus.equals(BuyCarStatus.CONSULT_REQUEST)){

            buyingCarService.registerBuyingCar(offerUser, buyingCarRegDTO);

            // 알림 등록---------------------------------------
            List<String> listArgs = new ArrayList<>();
            listArgs.add(car.getCarModel());
            listArgs.add(car.getCarNumber());
            // Locale 메시지 정보 가져오기
            String message = messageHandler.getMessage(MessageCode.fromValue(buyCarStatus.getName()), listArgs);
            userAlarmService.registerAlarm(carOwnerUser, message, buyingCarRegDTO.getAlarmContent(offerUser));
        }
        else{
            buyingCarService.updateBuyingCar(offerUser, buyingCarRegDTO);
        }

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("result", "success");
        return resultMap;
    }

    @ApiOperation(value = "구매 제안 리스트 전달", notes = "list 전달")
    @GetMapping("/list")
    public BuyingCarListResDTO<BuyingCarViewDTO> getBuyingCarList(PageRequestDTO pageRequestDTO, Long sellingCarId){

        return buyingCarService.getPageBuyingCarInfo(pageRequestDTO, sellingCarId);
    }

    @ApiOperation(value = "구매 희망 최고 가격", notes = "")
    @GetMapping("/highProposalPrice")
    public BuyingCarViewDTO getHighProposalPrice(Long sellingCarId){

        return buyingCarService.getHighProposalBuyingCar(sellingCarId);
    }

}