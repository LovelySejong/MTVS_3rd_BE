package com.mtvs.sejong.question.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecommendRequestDTO {

//    Long userId;
    List<QuestionDTO> questionList;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    class QuestionDTO {
        Long questionId;
        String questionType;
        String question;
        String answer;
        String difficultyLevel; // 문제 난이도
        Long popularityScore; // 인기도
        String questionFormat; // 문제 유형
        String worstQuestionType; // 취약 문제
    }
}
