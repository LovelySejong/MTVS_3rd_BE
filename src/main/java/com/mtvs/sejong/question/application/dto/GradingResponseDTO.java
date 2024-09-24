package com.mtvs.sejong.question.application.dto;

public class GradingResponseDTO {
    private int correctCount;
    private int totalQuestions;
    private int score;

    public GradingResponseDTO(int correctCount, int totalQuestions, int score) {
        this.correctCount = correctCount;
        this.totalQuestions = totalQuestions;
        this.score = score;
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public void setCorrectCount(int correctCount) {
        this.correctCount = correctCount;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
