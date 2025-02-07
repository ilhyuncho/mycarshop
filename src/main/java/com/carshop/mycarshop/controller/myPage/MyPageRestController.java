package com.carshop.mycarshop.controller.myPage;

import com.carshop.mycarshop.domain.user.User;
import com.carshop.mycarshop.dto.car.CarInfoReqDTO;
import com.carshop.mycarshop.dto.car.CarKmUpdateReqDTO;
import com.carshop.mycarshop.dto.car.CarRegisterReqDTO;
import com.carshop.mycarshop.dto.car.CarSimpleResDTO;
import com.carshop.mycarshop.dto.reference.RefCarSampleDTO;
import com.carshop.mycarshop.service.car.UserCarService;
import com.carshop.mycarshop.service.reference.RefCarSampleService;
import com.carshop.mycarshop.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/myPage")
@RequiredArgsConstructor
@Log4j2
@PreAuthorize("hasRole('USER')")
public class MyPageRestController {

    private final UserCarService userCarService;
    private final UserService userService;
    private final RefCarSampleService refCarSampleService;

    @ApiOperation(value = "내차 누적 주행거리 갱신", notes = "차 소유주가 등록")
    @PostMapping("/updateCarKm")
    public Map<String,String> postUpdateCarKm(@Valid @RequestBody CarKmUpdateReqDTO carKmUpdateReqDTO,
                                              BindingResult bindingResult, Principal principal){

        userCarService.modifyMyCarKm(carKmUpdateReqDTO);

        return new HashMap<>();
    }

    @ApiOperation(value = "나의 차량 조회", notes = "")
    @GetMapping("/findMyCar")
    public CarSimpleResDTO findMyCar(String carNumber){

        RefCarSampleDTO findCar = refCarSampleService.findMyCar(carNumber);

        return CarSimpleResDTO.builder()
                        .carNumber(findCar.getCarNumber())
                        .carDetailModel(findCar.getCarDetailModel())
                        .build();
    }

    @ApiOperation(value = "차 등록 (post)", notes = "")
    @PostMapping(value="/carRegister")
    public Map<String,String> postCarRegister(@RequestBody CarRegisterReqDTO carRegisterReqDTO, Principal principal){

        User user = userService.findUser(principal.getName());

        Long carId = userCarService.registerMyCar(user, carRegisterReqDTO.getCarNumber());

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("carId", carId.toString());
        return resultMap;
    }

    @ApiOperation(value = "차 세부 변경 (post)", notes = "")
    @PostMapping("/carModify")
    public Map<String,String> postCarModify(@RequestBody CarInfoReqDTO carInfoReqDTO, Principal principal ){

        userCarService.modifyMyCar(carInfoReqDTO);

        return new HashMap<>();
    }

}
