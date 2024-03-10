package com.teamproject.megabox.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "myMovieSeq", sequenceName = "movie_seq" ,allocationSize = 1)
@ToString
public class Movie{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "myMovieSeq")
    private Long mno;
    private String title;
    private String director;
    private String genre;
    private String duration;
    //Date타입으로 변경 필요할지도
    private String releaseYear;
    private int rating;
    private String posterURI;
    //nob??대용량 타입 혹은 어노테이션으로 설정 필요
    private String description;
    // 간단설명 필요 할듯 함
    //이미지갤러기 수업 끈나고 변수명 등등 수정 예정

}
