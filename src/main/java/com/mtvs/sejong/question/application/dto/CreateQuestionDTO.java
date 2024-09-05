//package com.mtvs.sejong.question.application.dto;
//
//import com.mtvs.sejong.question.domain.aggregate.Question;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@NoArgsConstructor
//@Getter
//public class CreateQuestionDTO {
//
//    private Long questionId;
//    private String questionType;
//    private String question;
//    private String answer;
//    private String difficultyLevel;
//    private int popularityScore;
//    private String questionFormat;
//
//    public CreateQuestionDTO(Long questionId, String questionType, String question, String answer, String difficultyLevel, int popularityScore, String questionFormat) {
//        this.questionId = questionId;
//        this.questionType = questionType;
//        this.question = question;
//        this.answer = answer;
//        this.difficultyLevel = difficultyLevel;
//        this.popularityScore = popularityScore;
//        this.questionFormat = questionFormat;
//    }
//
//    public CreateQuestionDTO(String questionType, String question, String answer, String difficultyLevel, int popularityScore, String questionFormat) {
//        this.questionType = questionType;
//        this.question = question;
//        this.answer = answer;
//        this.difficultyLevel = difficultyLevel;
//        this.popularityScore = popularityScore;
//        this.questionFormat = questionFormat;
//    }
//
////    Entity를 DTO로 변환
//    public static CreateQuestionDTO toQuestionRequestDTO(Question question) {
//        return new CreateQuestionDTO(
//                question.getQuestionId(),
//                question.getQuestionType(),
//                question.getQuestion(),
//                question.getAnswer(),
//                question.getDifficultyLevel(),
//                question.getPopularityScore(),
//                question.getQuestionFormat()
//        );
//    }
//
//    // DTO를 Entity로 변환
//    public Question toEntity() {
//        return new Question(
//                this.questionType,
//                this.question,
//                this.answer,
//                this.difficultyLevel,
//                this.popularityScore,
//                this.questionFormat
//        );
//    }
//
//}
