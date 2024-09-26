package com.mtvs.sejong.question.application.dto;

public class QuizScoreDTO {

    private String questionType;
    private double averageScore;

    public QuizScoreDTO(String questionType, double averageScore) {
        this.questionType = questionType;
        this.averageScore = averageScore;
    }

    public QuizScoreDTO() {}

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    @Override
    public String toString() {
        return "QuizScoreDTO{" +
                "questionType='" + questionType + '\'' +
                ", averageScore=" + averageScore +
                '}';
    }
}
