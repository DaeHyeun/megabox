package com.teamproject.megabox.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.atn.PredicateEvalInfo;

@Entity
@Table(name = "reservation")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SequenceGenerator(name = "reservation_seq", sequenceName = "reservation_seq", allocationSize = 1)
public class Reservation {
    @Id
    private Long id;
    private int price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id")
    private Theater theater;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id")
    private Seat seat;



}
