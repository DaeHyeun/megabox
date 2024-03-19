package com.teamproject.megabox.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private String email;           //이메일
    private String password;        //비밀번호
    private String name;            //이름
    private String zipcode;         //우편번호
    private String addr;            //주소
    private String addr_detail;     //상세주소
    private String pnumber;         //전화번호
}
