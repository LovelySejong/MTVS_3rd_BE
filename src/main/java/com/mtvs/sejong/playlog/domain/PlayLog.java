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

    @Column(name = "game_id")
    private Long gameId;

    public PlayLog() {}

    public PlayLog(Long id, int roomNumber, Long gameId) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.gameId = gameId;
    }

    public Long getId() {
        return id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    @Override
    public String toString() {
        return "PlayLog{" +
                "id=" + id +
                ", roomNumber='" + roomNumber + '\'' +
                ", gameId=" + gameId +
                '}';
    }
}

