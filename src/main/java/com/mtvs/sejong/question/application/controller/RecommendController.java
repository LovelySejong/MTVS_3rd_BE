package com.mtvs.sejong.question.application.controller;

import com.mtvs.sejong.question.application.dto.*;
import com.mtvs.sejong.question.domain.service.QuestionService;
import com.mtvs.sejong.question.openfeign.QuestionFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recommend")
public class RecommendController {

    private static final Logger log = LoggerFactory.getLogger(RecommendController.class);
    private final QuestionService questionService;
    private final QuestionFeignClient questionFeignClient;

    public RecommendController(QuestionService questionService, QuestionFeignClient questionFeignClient) {
        this.questionService = questionService;
        this.questionFeignClient = questionFeignClient;
    }

    @GetMapping
    public ResponseEntity<RecommendResponseDTO> recommend() {
        List<RecommendRequestDTO.QuestionDTO> requestDTOList = questionService.getRecommendedQuestions()
                .stream()
                .map(dto -> new RecommendRequestDTO.QuestionDTO(
                        dto.getQuestion_id(),
                        dto.getQuestion_type(),
                        dto.getQuestion(),
                        dto.getAnswer(),
                        dto.getDifficulty_level(),
                        dto.getCreated_at(),
                        dto.getPopularity_score(),
                        dto.getQuestion_format()
                ))
                .collect(Collectors.toList());

        RecommendRequestDTO requestDTO = new RecommendRequestDTO(requestDTOList);
        System.out.println("requestDTO = " + requestDTO);
        RecommendResponseDTO aiResponse = questionFeignClient.sendQuestions(requestDTO);

        List<Integer> questionIds = aiResponse.getProblems().stream()
                .map(RecommendResponseDTO.QuestionDTO::getQuestion_id)
                .collect(Collectors.toList());

        List<RecommendResponseDTO.QuestionDTO> problems = questionService.getQuestionsByIds(questionIds);

        RecommendResponseDTO responseDTO = new RecommendResponseDTO(problems);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/submit")
    public ResponseEntity<GradingResponseDTO> submitAnswers(@RequestBody AnswerSubmitRequestDTO answerSubmitRequestDTO) {
        int totalQuestions = answerSubmitRequestDTO.getAnswers().size();
        int correctCount = (int) answerSubmitRequestDTO.getAnswers().stream()
                .filter(answerDTO -> {
                    String correctAnswer = questionService.getCorrectAnswerById(answerDTO.getQuestionId());
                    return correctAnswer.equals(answerDTO.getSelectedAnswer());
                })
                .count();

        int score = (correctCount * 10) / totalQuestions;

        GradingResponseDTO gradingResponse = new GradingResponseDTO(correctCount, totalQuestions, score);
        return ResponseEntity.ok(gradingResponse);
    }
}
