package com.teamproject.megabox.dto;

import com.teamproject.megabox.constant.AgeRating;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {
    private Long id;
    private String title;           //영화제목
    private String subTitle;        //서브타이틀
    private String director;        //감독
    private String actor;           //배우
    private String nation;          //국가
    private String genre;           //장르
    private String duration;        //상영시간
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;       //개봉일
    @Enumerated(EnumType.STRING)
    private AgeRating ageRating;    //관람등급
    @Lob//BLOB타입 매핑
    private String description;     //상세설명
    private String attendance;      //관람객수
    private List<MovieImgDTO> movieImgDTOList = new ArrayList<>();
    private List<Long> movieImgIds = new ArrayList<>();





}
