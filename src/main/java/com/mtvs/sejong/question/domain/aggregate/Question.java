package com.mtvs.sejong.question.domain.aggregate;

import com.mtvs.sejong.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "question_answer")
public class Question extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionId;

    @Column(name = "question_type")
    private String questionType; // 문제 종류

    @Column(name = "question")
    private String question; // 문제 텍스트

    @Column(name = "answer")
    private String answer; // 정답

    @Column(name = "difficulty_level")
    private String difficultyLevel; // 문제 난이도

    @Column(name = "popularity_score")
    private int popularityScore; // 문제 인기도

    @Column(name = "question_format")
    private String questionFormat; // 문제 형식 (객관식, 주관식, 단답형, 서술형)

    @Column(name = "option1")
    private String option1; // 객관식 선택지 1

    @Column(name = "option2")
    private String option2; // 객관식 선택지 2

    @Column(name = "option3")
    private String option3; // 객관식 선택지 3

    @Column(name = "option4")
    private String option4; // 객관식 선택지 4

    @Builder
    public Question(String questionType, String question, String answer, String difficultyLevel,
                    int popularityScore, String questionFormat, String option1, String option2, String option3, String option4) {
        this.questionType = questionType;
        this.question = question;
        this.answer = answer;
        this.difficultyLevel = difficultyLevel;
        this.popularityScore = popularityScore;
        this.questionFormat = questionFormat;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
    }
}
