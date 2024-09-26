package com.mtvs.sejong.question.application.dto;

import java.util.List;

public class AnswerSubmitRequestDTO {
    private List<AnswerDTO> answers;

    public AnswerSubmitRequestDTO(List<AnswerDTO> answers) {
        this.answers = answers;
    }

    public AnswerSubmitRequestDTO() {}

    public List<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDTO> answers) {
        this.answers = answers;
    }

}
