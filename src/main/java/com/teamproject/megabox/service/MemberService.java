package com.teamproject.megabox.service;

import com.teamproject.megabox.constant.Role;
import com.teamproject.megabox.dto.MemberDTO;
import com.teamproject.megabox.entity.Member;

public interface MemberService {
    //회원가입
    Member saveMember(MemberDTO dto);

    //이메일 중복 체크
    String validateMember(MemberDTO dto);

    //이름이랑 전화번호로 이메일 찾기
    String findEmail(String name, String pnumber);

    //이름이랑 이메일로 회원 찾기
    String findByEmailName(String email, String name);

    //디폴트
    //디티오를 엔티티로
    default Member dtoToEntity(MemberDTO dto){
        Member member = Member.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .zipcode(dto.getZipcode())
                .addr(dto.getAddr())
                .addr_detail(dto.getAddr_detail())
                .pnumber(dto.getPnumber())
                .role(Role.USER)
                .build();
        return member;
    }

    //엔티티를 디티오로
    default MemberDTO entityToDTO(Member member){
        MemberDTO dto = MemberDTO.builder()
                .email(member.getEmail())
                .password(member.getPassword())
                .name(member.getName())
                .zipcode(member.getZipcode())
                .addr(member.getAddr())
                .addr_detail(member.getAddr_detail())
                .pnumber(member.getPnumber())
                .build();
        return dto;
    }
}
