package com.carshop.mycarshop.service.user;

import com.carshop.mycarshop.domain.user.User;
import com.carshop.mycarshop.dto.member.MemberJoinDTO;
import com.carshop.mycarshop.dto.user.UserAddressReqDTO;
import com.carshop.mycarshop.dto.user.UserDTO;
import com.carshop.mycarshop.dto.user.UserPasswordReqDTO;

public interface UserService {
    User findUser(String memberId);
    UserDTO findUserDTO(String memberId);
    User registerUser(MemberJoinDTO memberJoinDTO);
    User registerMainAddress(String memberId, UserAddressReqDTO userAddressReqDTO);
    void changePassword(String memberId, UserPasswordReqDTO userPasswordReqDTO);
}