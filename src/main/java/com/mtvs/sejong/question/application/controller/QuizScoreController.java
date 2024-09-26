package com.mtvs.sejong.question.application.controller;

import com.mtvs.sejong.question.application.dto.QuizScoreDTO;
import com.mtvs.sejong.question.domain.service.QuizScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.mtvs.sejong._core.utils.SecurityUtils.getCurrentUserId;

@RestController
@RequestMapping("/api/quiz")
public class QuizScoreController {

    private final QuizScoreService quizScoreService;

    public QuizScoreController(QuizScoreService quizScoreService) {
        this.quizScoreService = quizScoreService;
    }

    @GetMapping("/avgscores")
    public ResponseEntity<List<QuizScoreDTO>> getUserAverageScores(){
        Long userId = getCurrentUserId();
        List<QuizScoreDTO> averageScores = quizScoreService.getUserAverageScores(userId);
        return ResponseEntity.ok(averageScores);
    }
}
