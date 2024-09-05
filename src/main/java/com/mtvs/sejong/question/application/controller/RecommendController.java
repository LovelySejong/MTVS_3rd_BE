package com.mtvs.sejong.question.application.controller;

import com.mtvs.sejong.question.application.dto.RecommendRequestDTO;
import com.mtvs.sejong.question.application.dto.RecommendResponseDTO;
import com.mtvs.sejong.question.domain.service.QuestionService;
import com.mtvs.sejong.question.openfeign.QuestionFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/recommend")
public class RecommendController {

    private final QuestionService questionService;
    private final QuestionFeignClient questionFeignClient;

    public RecommendController(QuestionService questionService, QuestionFeignClient questionFeignClient) {
        this.questionService = questionService;
        this.questionFeignClient = questionFeignClient;
    }

    @PostMapping
    public ResponseEntity<?> recommend(@RequestBody RecommendRequestDTO.QuestionDTO questionDTO) {
        List<RecommendResponseDTO.RecommendQuestionDTO> requestDTO = questionService.findQuestionByType(questionDTO);
        List<RecommendResponseDTO.RecommendQuestionDTO> dto = questionFeignClient.sendQuestions(requestDTO);

        return ResponseEntity.ok(dto);
    }
}
