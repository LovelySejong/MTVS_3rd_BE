package com.mtvs.sejong.playtime.domain;

import com.mtvs.sejong.BaseTimeEntity;
import com.mtvs.sejong.user.domain.User;
import jakarta.persistence.Entity;

import jakarta.persistence.*;

@Entity
public class PlayTime extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private int roomId;

    @Column(nullable = false)
    private float clearTime;

    public PlayTime() {}

    public PlayTime(User user, int roomId, float clearTime) {
        this.user = user;
        this.roomId = roomId;
        this.clearTime = clearTime;
    }
}

