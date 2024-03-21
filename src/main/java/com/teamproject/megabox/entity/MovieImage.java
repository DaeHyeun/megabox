package com.teamproject.megabox.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "movie")
@SequenceGenerator(name = "myMovieImageSeq", sequenceName = "movieimg_seq",allocationSize = 1)
public class MovieImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "myMovieImageSeq")
    @Column(name = "movie_img_id")
    private Long id;
    private String uuid;
    private String imgName;
    private String path;
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;



}
