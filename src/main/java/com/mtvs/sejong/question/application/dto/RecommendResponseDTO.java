package com.mtvs.sejong.question.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecommendResponseDTO {

    List<RecommendQuestionDTO> recommendedProblems;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    class RecommendQuestionDTO {
        Long questionId;
        String questionType;
        String question;
        String answer;
        String difficultyLevel;
        Long popularityScore;
        String questionFormat;
    }
}
