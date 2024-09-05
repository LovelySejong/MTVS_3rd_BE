package com.mtvs.sejong.question.openfeign;

import com.mtvs.sejong.question.application.dto.RecommendResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "question-service", url = "http://localhost:7880/recommend") // ai 서버작성
public interface QuestionFeignClient {

    @PostMapping
    List<RecommendResponseDTO.RecommendQuestionDTO> sendQuestions(List<RecommendResponseDTO.RecommendQuestionDTO> recommendQuestionDTOList);
}
