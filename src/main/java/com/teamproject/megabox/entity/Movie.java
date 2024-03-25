package com.teamproject.megabox.entity;

import com.teamproject.megabox.constant.AgeRating;
import com.teamproject.megabox.dto.MovieDTO;
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

    //수정시 필드 초기화 해주는 작업
    //setter OR Builder 사용 가능
    public void updateMovie(MovieDTO dto){
        this.title = dto.getTitle();
        this.subTitle = dto.getSubTitle();
        this.director = dto.getDirector();
        this.actor = dto.getActor();
        this.nation = dto.getNation();
        this.genre = dto.getGenre();
        this.duration = dto.getDuration();
        this.releaseDate = dto.getReleaseDate();
        this.ageRating = dto.getAgeRating();
        this.description = dto.getDescription();
    }




}
