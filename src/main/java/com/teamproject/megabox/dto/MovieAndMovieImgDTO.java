package com.teamproject.megabox.dto;

import com.teamproject.megabox.constant.AgeRating;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieAndMovieImgDTO {
    private Long id;
    private String title;           //영화제목
    private String genre;           //장르
    private AgeRating ageRating;    //관람등급

    private String imgUrl;
}
