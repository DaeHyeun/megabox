package com.teamproject.megabox.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "movie_img")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SequenceGenerator(name = "movie_seq", sequenceName = "movie_seq", allocationSize = 1)
public class MovieImg {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "movie_seq")
    @Column(name = "movie_img_id")
    private Long id;
    private String imgName;     //데이터베이스에 저장될 이미지 이름
    private String oriImgName;  //원본 이미지 이름
    private String imgUrl;      //이미지 저장될 경로
    private String repImgYn;    //대표이미지 여부
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;        //다대일 관계

    public void update(String oriImgName, String imgName, String imgUrl){
        this.oriImgName=oriImgName;
        this.imgName=imgName;
        this.imgUrl=imgUrl;
    }
}
