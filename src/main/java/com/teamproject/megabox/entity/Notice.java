package com.teamproject.megabox.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notice")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SequenceGenerator(name = "notice_seq", sequenceName = "notice_seq", allocationSize = 1)
public class Notice extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "notice_seq")
    @Column(name = "notice_id")
    private Long id;                            //글번호
    private String title;                       //글제목
    private String content;                     //글내용
}
