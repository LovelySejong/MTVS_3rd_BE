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

    @Builder
    public Question(String questionType, String question, String answer, String difficultyLevel,
                    int popularityScore, String questionFormat) {
        this.questionType = questionType;
        this.question = question;
        this.answer = answer;
        this.difficultyLevel = difficultyLevel;
        this.popularityScore = popularityScore;
        this.questionFormat = questionFormat;
    }
}
