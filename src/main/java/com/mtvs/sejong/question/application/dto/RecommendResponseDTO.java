package com.mtvs.sejong.question.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecommendResponseDTO {

    private List<QuestionDTO> problems;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QuestionDTO {
        private int question_id;
        private String question_type;
        private String question;
        private String answer;
        private String difficulty_level;
        private String created_at; // LocalDateTime을 String으로 변환
        private int popularity_score;
        private String question_format;
        private String option1;
        private String option2;
        private String option3;
        private String option4;
    }
}
