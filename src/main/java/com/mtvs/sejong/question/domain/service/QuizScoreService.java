package com.mtvs.sejong.question.domain.service;

import com.mtvs.sejong.question.domain.aggregate.QuizScore;
import com.mtvs.sejong.question.domain.repository.QuizScoreRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class QuizScoreService {

    private final QuizScoreRepository quizScoreRepository;

    public QuizScoreService(QuizScoreRepository quizScoreRepository) {
        this.quizScoreRepository = quizScoreRepository;
    }

    public void saveQuizScore(Long userId, String questionType, int score) {
        QuizScore quizScore = new QuizScore(userId, questionType, score);
        quizScoreRepository.save(quizScore);
    }

}
