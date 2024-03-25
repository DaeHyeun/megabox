package com.teamproject.megabox.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "review")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SequenceGenerator(name = "review_seq", sequenceName = "review_seq", allocationSize = 1)
public class Review extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "review_seq")
    @Column(name = "review_id")
    private Long id;
    private String comments;         //코멘트
    private double movieRating;     //별점
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;                 //다대일 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;              //다대일 관계

}
