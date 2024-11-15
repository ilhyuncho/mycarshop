package com.carshop.mycarshop.controller.auth;

import com.carshop.mycarshop.common.event.userRegister.UserRegistrationEvent;
import com.carshop.mycarshop.common.exception.member.MemberTaskException;
import com.carshop.mycarshop.common.message.MessageCode;
import com.carshop.mycarshop.common.message.MessageHandler;
import com.carshop.mycarshop.common.util.Util;
import com.carshop.mycarshop.domain.member.Member;
import com.carshop.mycarshop.domain.user.User;
import com.carshop.mycarshop.dto.member.MemberJoinDTO;
import com.carshop.mycarshop.service.member.MemberService;
import com.carshop.mycarshop.service.user.UserAlarmService;
import com.carshop.mycarshop.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
@Log4j2

public class AuthController {

    private final MemberService memberService;
    private final UserService userService;
    private final UserAlarmService userAlarmService;
    private final MessageHandler messageHandler;

    private final ApplicationEventPublisher eventPublisher;

    @GetMapping("/login")
    public void login(String error, String logout){

        log.error("login get................");

        // 로그아웃 호출 URL : http://localhost:8090/logout
        // 으로 redirect : http://localhost:8090/auth/login?logout
        if( logout != null){
            log.error("user logout======================");
        }
    }
    @GetMapping("/register")
    public void register(){

    }

    @PostMapping("/register")
    public String postRegister(MemberJoinDTO memberJoinDTO, RedirectAttributes redirectAttributes){

        // 테스트 용
        if(memberJoinDTO.getMemberId().isEmpty()){
            memberJoinDTO.setMemberId(Util.createRandomName("member"));
        }

        try{
            Member member = memberService.registerMember(memberJoinDTO);
            User user = userService.registerUser(memberJoinDTO);

            // 유저 등록 이후 이메일 인증 이벤트 발생
            eventPublisher.publishEvent(new UserRegistrationEvent(member));

            // 알림 등록---------------------------------------
            // Locale 메시지 정보 가져오기
            List<String> listArgs = new ArrayList<>();
            listArgs.add(user.getUserName());
            String messageTitle = messageHandler.getMessage(MessageCode.WELCOME_REGISTER_TITLE, listArgs);
            String messageContent = messageHandler.getMessage(MessageCode.WELCOME_REGISTER_CONTENT, Collections.emptyList());

            userAlarmService.registerAlarm(user, messageTitle, messageContent);

        }catch (MemberService.MemberIdExistException ex){
            redirectAttributes.addFlashAttribute("error", "memberId");
            return "redirect:/auth/register";
        }catch (MemberTaskException ex){
            //log.error("MemberTaskException()~~~~~~~~~~~~~ : " + ex.getMsg() + "," + ex.getCode());
            redirectAttributes.addFlashAttribute("error", ex.getMsg());
            return "redirect:/auth/register";
        }

        redirectAttributes.addFlashAttribute("result", "success");
        return "redirect:/auth/login";
    }

    @ApiOperation(value = "패스워드를 잊어버렸을때", notes = "패스워드 재설정 페이지로 이동")
    @GetMapping("/password")
    public void password(){

    }

    @ApiOperation(value = "이메일 인증 성공", notes = "로그인 페이지로 이동")
    @GetMapping("/loginVerified")
    public String loginVerified(Model model) {
        model.addAttribute("loginVerified", true);
        return "auth/login";
    }

    @GetMapping("/loginError")
    public String loginError(Model model) {
        log.error("loginError()~!~~~~");

        model.addAttribute("loginError", true);
        return "auth/login";
    }

    @ApiOperation(value = "아직 이메일 미인증", notes = "로그인 페이지로 이동")
    @GetMapping("/loginDisabled")
    public String loginDisabled(Model model) {
        log.error("loginDisabled()~!~~~~");

        model.addAttribute("loginDisabled", true);
        return "auth/login";
    }
    @ApiOperation(value = "비밀번호3회 틀림", notes = "로그인 페이지로 이동")
    @GetMapping("/loginLocked")
    public String loginLocked(Model model) {
        log.error("loginLocked()~!~~~~");

        model.addAttribute("loginLocked", true);
        return "auth/login";
    }



}

