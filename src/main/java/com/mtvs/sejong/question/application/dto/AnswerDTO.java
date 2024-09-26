package com.mtvs.sejong.question.application.dto;

public class AnswerDTO {
    private Long userId;
    private int questionId;
    private String selectedAnswer;

    public AnswerDTO(Long userId, int questionId, String selectedAnswer) {
        this.userId = userId;
        this.questionId = questionId;
        this.selectedAnswer = selectedAnswer;
    }

    public AnswerDTO(){}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

}
