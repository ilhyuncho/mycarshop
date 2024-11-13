package com.carshop.mycarshop.service.user;

import com.carshop.mycarshop.common.exception.UserNotFoundException;
import com.carshop.mycarshop.common.exception.member.MemberExceptions;
import com.carshop.mycarshop.domain.member.Member;
import com.carshop.mycarshop.domain.member.MemberRepository;
import com.carshop.mycarshop.domain.user.User;
import com.carshop.mycarshop.domain.user.UserGradeType;
import com.carshop.mycarshop.domain.user.UserRepository;
import com.carshop.mycarshop.dto.member.MemberJoinDTO;
import com.carshop.mycarshop.dto.user.UserAddressReqDTO;
import com.carshop.mycarshop.dto.user.UserDTO;
import com.carshop.mycarshop.dto.user.UserPasswordReqDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findUser(String memberId) {
        return userRepository.findByMemberId(memberId)
                .orElseThrow(()-> new UserNotFoundException("해당 유저는 존재하지 않습니다"));
    }
    @Override
    public UserDTO findUserDTO(String memberId){
        User user = findUser(memberId);
        return entityToDTO(user);
    }
    @Override
    public User registerUser(MemberJoinDTO memberJoinDTO) {
        User user = User.builder()
                .userName(memberJoinDTO.getMemberName())    // 유저 이름
                .memberId(memberJoinDTO.getMemberId())
                .mPoint(0)
                .mGrade(UserGradeType.GRADE_A)
                .build();

        return userRepository.save(user);
    }
    @Override
    public User registerMainAddress(String memberId, UserAddressReqDTO userAddressReqDTO) {

        User user = findUser(memberId);

        user.registerMainAddress(userAddressReqDTO);

        return user;
    }

    @Override
    public void changePassword(String memberId, UserPasswordReqDTO userPasswordReqDTO) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(MemberExceptions.NOT_FOUND::get);

        boolean matches = passwordEncoder.matches(userPasswordReqDTO.getCurrentPassword(), member.getMemberPw());

        if(matches){
            member.changePassword(passwordEncoder.encode(userPasswordReqDTO.getNewPassword()));
        }
        else{
            throw MemberExceptions.PASSWORD_NOT_SAME.get();
        }
    }
    private static UserDTO entityToDTO(User user) {
        return UserDTO.builder()
                .userID(user.getUserId())
                .userName(user.getUserName())
                .memberId(user.getMemberId())
                .address(user.getAddress() != null ? user.getAddress().fullAddress() : null)
                .billingAddress(user.getBillingAddress() != null ? user.getBillingAddress().fullAddress() : null)
                .mPoint(user.getMPoint())
                .mGrade(user.getMGrade())
                .build();
    }

}