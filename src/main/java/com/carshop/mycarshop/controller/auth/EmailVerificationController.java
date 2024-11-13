package com.carshop.mycarshop.controller.auth;

import com.carshop.mycarshop.domain.member.Member;
import com.carshop.mycarshop.service.member.EmailVerificationService;
import com.carshop.mycarshop.service.member.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Base64;

@Controller
@RequiredArgsConstructor
@Log4j2
public class EmailVerificationController {

    private final EmailVerificationService verificationService;

    private final MemberService memberService;

    @ApiOperation(value = "이메일 인증 요청", notes = "이메일로 전달 된 메시지의 link를 클릭시")
    @GetMapping("/verify/email")
    public String verifyEmail(@RequestParam String id){

        log.error("verifyEmail() call!!! id : " + id);

        byte[] decodeId = Base64.getDecoder().decode(id.getBytes());
        String memberId = verificationService.getVerificationByVerificationId(new String(decodeId));

        if(memberId != null){
            Member member = memberService.findByMemberId(memberId);
            member.setVerified(true);
            memberService.updateMember(member);
            return "redirect:/auth/loginVerified";
        }
        return "redirect:/auth/loginError";
    }

}
