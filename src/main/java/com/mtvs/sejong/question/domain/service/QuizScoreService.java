package com.mtvs.sejong.question.domain.service;

import com.mtvs.sejong.question.application.dto.QuizScoreDTO;
import com.mtvs.sejong.question.domain.aggregate.QuizScore;
import com.mtvs.sejong.question.domain.repository.QuizScoreRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    public List<QuizScoreDTO> getUserAverageScores(Long userId) {
        return quizScoreRepository.findAverageScoreByUserId(userId);
    }
}
