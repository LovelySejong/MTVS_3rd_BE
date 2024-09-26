package com.mtvs.sejong.question.domain.aggregate;

import com.mtvs.sejong.BaseTimeEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "quiz_score")
public class QuizScore extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scoreId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "question_type")
    private String questionType;

    @Column(name = "score")
    private int score;

    public QuizScore() {}

    public QuizScore(Long userId, String questionType, int score) {
        this.userId = userId;
        this.questionType = questionType;
        this.score = score;
    }

    public Long getScoreId() {
        return scoreId;
    }

    public void setScoreId(Long scoreId) {
        this.scoreId = scoreId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "QuizScore{" +
                "scoreId=" + scoreId +
                ", userId=" + userId +
                ", questionType='" + questionType + '\'' +
                ", score=" + score +
                '}';
    }
}
