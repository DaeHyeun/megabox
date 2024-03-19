package com.teamproject.megabox.entity;

import com.teamproject.megabox.constant.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Controller;

@Entity
@Table(name = "member")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SequenceGenerator(name = "member_seq", sequenceName = "member_seq", allocationSize = 1)
public class Member extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "member_seq")
    @Column(name = "member_id")
    private Long id;                //시퀀스
    @Column(unique = true)
    private String email;           //이메일
    private String password;        //비밀번호
    private String name;            //이름
    private String zipcode;         //우편번호
    private String addr;            //주소
    private String addr_detail;     //상세주소
    private String pnumber;         //전화번호
    @Enumerated(EnumType.STRING)
    private Role role;              //역할
}
