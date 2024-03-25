package com.teamproject.megabox.dto;

//발송할 이메일 내용(정보)을 저장할 DTO

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MailDTO {
    private String address;
    private String title;
    private String message;
}
