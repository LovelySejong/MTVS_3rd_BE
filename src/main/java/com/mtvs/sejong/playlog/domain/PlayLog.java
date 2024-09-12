package com.mtvs.sejong.playlog.domain;

import com.mtvs.sejong.BaseTimeEntity;
import jakarta.persistence.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class PlayLog extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_name", nullable = false)
    private String roomName;

    @Column(name = "entry_time", nullable = false)
    private LocalDateTime entryTime;

    @Column(name = "exit_time")
    private LocalDateTime exitTime;

    @Column(name = "user_id")
    private Long userId;

    public PlayLog() {}

    public PlayLog(Long id, String roomName, LocalDateTime entryTime, LocalDateTime exitTime, Long userId) {
        this.id = id;
        this.roomName = roomName;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public String getRoomName() {
        return roomName;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "PlayLog{" +
                "id=" + id +
                ", roomName='" + roomName + '\'' +
                ", entryTime=" + entryTime +
                ", exitTime=" + exitTime +
                ", userId='" + userId + '\'' +
                '}';
    }
}

