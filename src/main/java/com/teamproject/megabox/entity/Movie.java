package com.teamproject.megabox.entity;

import com.teamproject.megabox.constant.AgeRating;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SequenceGenerator(name = "myMovieSeq", sequenceName = "movie_seq", allocationSize = 1)
public class Movie extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "myMovieSeq")
    @Column(name = "movie_id")
    private Long id;                
    private String title;           //영화제목
    private String subTitle;        //서브타이틀
    private String director;        //감독
    private String actor;           //배우
    private String nation;          //국가
    private String genre;           //장르
    private String duration;        //상영시간
    private Date releaseDate;       //개봉일
    @Enumerated(EnumType.STRING)
    private AgeRating ageRating;    //관람등급
    @Lob//BLOB타입 매핑
    private String description;     //상세설명
    private String attendance;      //관람객수


}
