package com.mtvs.sejong.playlog.domain;

import com.mtvs.sejong.BaseTimeEntity;
import jakarta.persistence.Entity;

import jakarta.persistence.*;

@Entity
public class PlayLog extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_number", nullable = false)
    private int roomNumber;


    @Column(name = "user_id")
    private Long userId;

    public PlayLog() {}

    public PlayLog(Long id, int roomNumber, Long userId) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "PlayLog{" +
                "id=" + id +
                ", roomNumber='" + roomNumber + '\'' +
                ", userId=" + userId +
                '}';
    }
}

