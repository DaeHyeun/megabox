package com.teamproject.megabox.controller;

import com.teamproject.megabox.dto.MemberDTO;
import com.teamproject.megabox.entity.Member;
import com.teamproject.megabox.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/join")
    public String memberForm(){
        return "member/memberForm";
    }

    @PostMapping("/join")
    public String joinProcess(MemberDTO dto){
        Member member = memberService.saveMember(dto);
        System.out.println("가입된 회원 : "+member.toString());
        return "redirect:/";
    }






}
