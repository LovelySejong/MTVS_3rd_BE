package com.mtvs.sejong.playlog.dto;

public class PlayLogScoreDTO {

    private int roomNumber; // 문제 유형
    private double averageScore; // 평균 시간

    public PlayLogScoreDTO(int roomNumber, double averageScore) {
        this.roomNumber = roomNumber;
        this.averageScore = averageScore;
    }

    public PlayLogScoreDTO() {}

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    @Override
    public String toString() {
        return "PlayLogScoreDTO{" +
                "roomNumber=" + roomNumber +
                ", averageScore=" + averageScore +
                '}';
    }
}
