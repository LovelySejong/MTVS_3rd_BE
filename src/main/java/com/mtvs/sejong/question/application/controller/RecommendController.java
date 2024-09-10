package com.mtvs.sejong.question.application.controller;

import com.mtvs.sejong.question.application.dto.RecommendResponseDTO;
import com.mtvs.sejong.question.domain.service.QuestionService;
import com.mtvs.sejong.question.openfeign.AIResponseDto;
import com.mtvs.sejong.question.openfeign.QuestionFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResponseEntity<?> recommend() {
        List<RecommendResponseDTO.RecommendQuestionDTO> requestDTO = questionService.findQuestionByType(); // DB에서 조회
        List<RecommendResponseDTO.RecommendQuestionDTO> dto = questionFeignClient.sendQuestions(requestDTO); // AI한테 결과 받아오기

        return ResponseEntity.ok(dto); // 프론트로 값 넘기기
    }

    @GetMapping("/test")
    public AIResponseDto test() {
        return questionFeignClient.test();
    }
}
