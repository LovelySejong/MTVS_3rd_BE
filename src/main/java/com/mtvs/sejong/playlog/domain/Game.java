package com.mtvs.sejong.playlog.domain;

import com.mtvs.sejong.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "tbl_game")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Game extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;
    @Column
    private Long partnerId;

    @Builder
    public Game(Long userId, Long partnerId) {
        this.userId = userId;
        this.partnerId = partnerId;
    }
}
