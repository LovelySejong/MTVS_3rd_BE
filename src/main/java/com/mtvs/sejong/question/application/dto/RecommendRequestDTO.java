package com.mtvs.sejong.question.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mtvs.sejong.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecommendRequestDTO {

//    Long userId;
    List<QuestionDTO> input;

    @Getter
    @NoArgsConstructor
//    @AllArgsConstructor
    public static class QuestionDTO {
        int question_id;
        String question_type;
        String question;
        String answer;
        String difficulty_level;
//        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX")
        String created_at;
        int popularity_score;
        String question_format;

        @Builder
        public QuestionDTO(int questionId, String questionType, String question, String answer, String difficultyLevel, LocalDateTime createdAt, int popularityScore, String questionFormat) {
            this.question_id = questionId;
            this.question_type = questionType;
            this.question = question;
            this.answer = answer;
            this.difficulty_level = difficultyLevel;
            this.created_at = createdAt.toString();
            this.popularity_score = popularityScore;
            this.question_format = questionFormat;
        }
    }
}
