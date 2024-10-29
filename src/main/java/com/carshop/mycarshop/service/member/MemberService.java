package com.carshop.mycarshop.service.member;

import com.carshop.mycarshop.common.jwt.util.MemberDTO;
import com.carshop.mycarshop.dto.member.MemberJoinDTO;

public interface MemberService {

    static class MemberIdExistException extends  Exception{

    }
    void registerMember(MemberJoinDTO memberJoinDTO) throws MemberIdExistException;

    MemberDTO getMember(String memberId, String memberPw);
}