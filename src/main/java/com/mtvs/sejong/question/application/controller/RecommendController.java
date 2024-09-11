package com.mtvs.sejong.question.application.controller;

import com.mtvs.sejong.question.application.dto.RecommendRequestDTO;
import com.mtvs.sejong.question.application.dto.RecommendResponseDTO;
import com.mtvs.sejong.question.domain.service.QuestionService;
import com.mtvs.sejong.question.openfeign.QuestionFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        RecommendResponseDTO responseDTO = questionFeignClient.sendQuestions(requestDTO);

        return ResponseEntity.ok(responseDTO);
    }
}
