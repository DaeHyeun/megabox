package com.teamproject.megabox.service;

import com.teamproject.megabox.dto.MemberDTO;
import com.teamproject.megabox.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("회원가입 테스트")
    public void insertTest(){
        MemberDTO dto = MemberDTO.builder()
                .email("admin@megabox.com")
                .password("1234")
                .name("운영자")
                .zipcode("12345")
                .addr("울산시 남구")
                .addr_detail("1번지")
                .pnumber("01000000000")
                .build();

        Member member = memberService.saveMember(dto);
        System.out.println(member.toString());
    }
}
