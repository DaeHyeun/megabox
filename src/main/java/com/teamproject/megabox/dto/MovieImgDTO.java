package com.teamproject.megabox.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieImgDTO {
    private Long id;
    private String imgName;     //데이터베이스에 저장될 이미지 이름
    private String oriImgName;  //원본 이미지 이름
    private String imgUrl;      //이미지 저장될 경로
    private String repImgYn;    //대표이미지 여부

}
