package com.teamproject.megabox.controller;

import com.teamproject.megabox.dto.MailDTO;
import com.teamproject.megabox.dto.MemberDTO;
import com.teamproject.megabox.service.MemberService;
import com.teamproject.megabox.service.SendEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.FailedLoginException;
import java.util.Objects;

@RestController
@RequestMapping("/findMember")
@RequiredArgsConstructor
public class FindMemberController {

    public final MemberService memberService;
    public final SendEmailService sendEmailService;

    //이름과 전화번호로 회원 찾기
    @PostMapping("/findId")
    public ResponseEntity<String> findByNamePnumber(@RequestBody MemberDTO memberDTO){
        String name = memberDTO.getName();
        String pnumber = memberDTO.getPnumber();
        String memberEmail = memberService.findEmail(name, pnumber);

        //가입된 정보가 없을 경우
        if (memberEmail.equals("fail")){
            return new ResponseEntity<>("입력하신 정보로 가입된 회원이 없습니다.", HttpStatus.OK);
        }
        return new ResponseEntity<>("사용하신 이메일은 "+memberEmail+"입니다.", HttpStatus.OK);
    }

    //이름과 이메일로 회원 찾기
    @PostMapping("/resetPass")
    public ResponseEntity<String> findByEmailName(@RequestBody MemberDTO memberDTO){
        String email = memberDTO.getEmail();
        String name = memberDTO.getName();
        String result = memberService.findByEmailName(email, name);
        if (result.equals("ok")){
            return new ResponseEntity<>("ok", HttpStatus.OK);
        }
        return new ResponseEntity<>("fail", HttpStatus.OK);
    }
    //등록된 이메일로 임시비밀번호를 발송하고 발송된 임시비밀번호로 사용자의 pw를 변경하는 컨트롤러
    @PostMapping("/resetPass/sendEmail")
    public void sendEmail(@RequestBody MemberDTO memberDTO){
        System.out.println("controller까지 왔다!!!!");
        MailDTO dto = sendEmailService.createMailAndChangePassword(memberDTO.getEmail(), memberDTO.getName());
        System.out.println(dto.toString());
        sendEmailService.mailSend(dto);
    }

    //이메일 중복 확인하기
    @PostMapping("/emailCheck")
    public ResponseEntity<String> findEmail(@RequestBody MemberDTO memberDTO){
        String result = memberService.validateMember(memberDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
