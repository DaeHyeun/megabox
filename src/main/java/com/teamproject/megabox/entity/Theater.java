package com.teamproject.megabox.entity;

import com.teamproject.megabox.constant.SellStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "theater")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SequenceGenerator(name = "theater_seq", sequenceName = "theater_seq", allocationSize = 1)
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "theater_seq")
    @Column(name = "theater_id")
    private Long id;
    private String type;            //관의 타입
    private int price;              //가격
    private int totalSeat;          //전체 좌석
    @Enumerated(EnumType.STRING)
    private SellStatus sellStatus;  //관이 매진인지 아닌지
}
