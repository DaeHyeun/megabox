package com.teamproject.megabox.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "movieSchedule")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SequenceGenerator(name = "movieSchedule_seq", sequenceName = "movieSchedule_seq", allocationSize = 1)
public class MovieSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "movieSchedule_seq")
    @Column(name = "movieSchedule_id")
    private Long id;
    private String startTime;
    private String date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id")
    private Theater theater;


}
