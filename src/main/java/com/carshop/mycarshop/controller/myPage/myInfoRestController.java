package com.carshop.mycarshop.controller.myPage;

import com.carshop.mycarshop.domain.user.Address;
import com.carshop.mycarshop.domain.user.User;
import com.carshop.mycarshop.dto.PageRequestDTO;
import com.carshop.mycarshop.dto.order.OrderDeliveryResDTO;
import com.carshop.mycarshop.dto.user.*;
import com.carshop.mycarshop.service.shop.OrderService;
import com.carshop.mycarshop.service.user.UserAddressBookService;
import com.carshop.mycarshop.service.user.UserPointHistoryService;
import com.carshop.mycarshop.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/myInfo")
@RequiredArgsConstructor
@Log4j2
@PreAuthorize("hasRole('USER')")
public class myInfoRestController {
    private final UserPointHistoryService userPointHistoryService;
    private final UserAddressBookService userAddressBookService;
    private final UserService userService;
    private final OrderService orderService;

    @ApiOperation(value = "나의 포인트 정보 조회", notes = "")
    @GetMapping("/getMyPoint")
    public UserListPointHistoryResDTO<UserPointHistoryResDTO>
    getMyPoint(@ModelAttribute("pageRequestDto") PageRequestDTO pageRequestDTO,
               UserPointHistoryReqDTO userPointHistoryReqDTO, Principal principal){

        User user = userService.findUser(principal.getName());

        return userPointHistoryService.getListUserPointHistory(pageRequestDTO, user, userPointHistoryReqDTO);
    }

    @ApiOperation(value = "배송 주소 정보 get", notes = "")
    @GetMapping("/addressInfo")
    public UserAddressBookResDTO addressInfo(Long userAddressBookId){
        return userAddressBookService.getUserAddressBookInfo(userAddressBookId);
    }
    @ApiOperation(value = "모든 배송 주소 정보 get", notes = "")
    @GetMapping("/allAddressInfo")
    //@PreAuthorize("principal.username != #userName")
    public List<UserAddressBookResDTO> allAddressInfo(String memberId){

        User user = userService.findUser(memberId);

        return userAddressBookService.getAllUserAddressBookInfo(user);
    }

    @ApiOperation(value = "배송지 추가", notes = "")
    @PostMapping("/registerDeliveryAddress")
    public Map<String,String> postRegisterDeliveryAddress(@Valid @RequestBody UserAddressBookReqDTO userAddressBookReqDTO,
                                                          BindingResult bindingResult,
                                                          Principal principal ) throws BindException {
        if(bindingResult.hasErrors()){
            log.error("has errors.....");
            throw new BindException(bindingResult);
        }
        User user = userService.findUser(principal.getName());

        userAddressBookService.registerAddressBook(user, userAddressBookReqDTO);

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("result", "success");
        return resultMap;
    }

    @ApiOperation(value = "배송지 수정", notes = "")
    @PostMapping("/modifyDeliveryAddress")
    public Map<String,String> postModifyDeliveryAddress(@Valid @RequestBody UserAddressBookReqDTO userAddressBookReqDTO,
                                                        BindingResult bindingResult,
                                                        Principal principal ) throws BindException {
        if(bindingResult.hasErrors()){
            log.error("has errors.....");
            throw new BindException(bindingResult);
        }

        User user = userService.findUser(principal.getName());

        userAddressBookService.modifyAddressBook(user, userAddressBookReqDTO);

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("result", "success");
        return resultMap;
    }

    @ApiOperation(value = "배송지 주소 삭제", notes = "")
    @PostMapping("/deleteAddress/{userAddressBookId}")
    public Map<String,String> postDeleteAddress(@PathVariable(name="userAddressBookId") Long userAddressBookId,
                                                Principal principal) {

        User user = userService.findUser(principal.getName());

        log.error(userAddressBookId);

        userAddressBookService.deleteAddressBook(user, userAddressBookId);

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("result", "success");
        return resultMap;
    }

    @ApiOperation(value = "배송 진행 상황 get", notes = "")
    @GetMapping("/orderDeliveryProcess")
    public OrderDeliveryResDTO orderDeliveryProcess(Long orderId){
        return orderService.getOrderDeliveryProcess(orderId);
    }

    @ApiOperation(value = "기본 주소 정보 get", notes = "")
    @GetMapping("/getMainAddress")
    public Address getMainAddress(Principal principal){

        User user = userService.findUser(principal.getName());
        return user.getAddress();
    }

    @ApiOperation(value = "기본 주소 추가", notes = "")
    @PostMapping("/registerMainAddress")
    public Map<String,String> postRegisterMainAddress(@Valid @RequestBody UserAddressReqDTO userAddressReqDTO,
                                                      BindingResult bindingResult,
                                                      Principal principal) throws BindException {
        if(bindingResult.hasErrors()){
            log.error("has errors.....");
            throw new BindException(bindingResult);
        }

        User user = userService.registerMainAddress(principal.getName(), userAddressReqDTO);

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("result", "success");
        resultMap.put("fullAddress", user.getAddress().fullAddress() );
        return resultMap;
    }

    @ApiOperation(value = "비밀번호 변경", notes = "")
    @PostMapping("/changePassword")
    public Map<String,String> postChangePassword(@Valid @RequestBody UserPasswordReqDTO userPasswordReqDTO,
                                                 BindingResult bindingResult,
                                                 Principal principal) throws BindException {
        if(bindingResult.hasErrors()){
            log.error("has errors.....");
            throw new BindException(bindingResult);
        }

        userService.changePassword(principal.getName(), userPasswordReqDTO);

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("result", "success");
        return resultMap;
    }


}