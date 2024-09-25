package com.mtvs.sejong.question.application.dto;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RecommendRequestDTO {

    private List<QuestionDTO> problems;

    @Getter
    @NoArgsConstructor
//    @AllArgsConstructor
    @ToString
    public static class QuestionDTO {
        private int question_id;
        private String question_type;
        private String question;
        private String answer;
        private String difficulty_level;
        private String created_at; // LocalDateTime을 String으로 변환
        private int popularity_score;
        private String question_format;

        @Builder
        public QuestionDTO(int questionId, String questionType, String question, String answer, String difficultyLevel, String createdAt, int popularityScore, String questionFormat) {
            this.question_id = questionId;
            this.question_type = questionType;
            this.question = question;
            this.answer = answer;
            this.difficulty_level = difficultyLevel;
            this.created_at = createdAt;
            this.popularity_score = popularityScore;
            this.question_format = questionFormat;
        }
    }
}
