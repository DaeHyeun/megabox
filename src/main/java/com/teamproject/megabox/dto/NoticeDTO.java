package com.teamproject.megabox.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDTO {
    private Long id;                            //글번호
    private String title;                       //글제목
    private String content;                     //글내용
}
