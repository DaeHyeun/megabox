package com.teamproject.megabox.entity;

import com.teamproject.megabox.constant.SellStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "seat")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Seat {
    @Id
    @Column(name = "seat_id")
    private String id;                  //프라이머리키  ("관id_seatName")
    private String seatName;            //행 * 열 (a1)
    private String col;                 //열(숫자)
    private String line;                 //행(영어)
    private SellStatus sellStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id")
    private Theater theater;            //다대일관계
}
