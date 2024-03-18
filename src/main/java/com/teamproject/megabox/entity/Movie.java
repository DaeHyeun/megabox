package com.teamproject.megabox.entity;

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
    private Long movieSeq ;
    private String title;
    private String subTitle;
    private String director;
    private String actor;
    private String nation;
    private String genre;
    private String duration;
    private Date releaseDate;
    private String rating;
    private String posterURL;
    @Lob//BLOB타입 매핑
    private String description;
    private int attendance;


}
