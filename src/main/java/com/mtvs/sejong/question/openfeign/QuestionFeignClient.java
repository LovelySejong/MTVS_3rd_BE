package com.mtvs.sejong.question.openfeign;

import com.mtvs.sejong.question.application.dto.RecommendResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "question-service", url = "http://192.168.0.64:7880") // ai 서버작성
public interface QuestionFeignClient {

    @PostMapping(value ="/recommend")
    List<RecommendResponseDTO.RecommendQuestionDTO> sendQuestions(@RequestBody List<RecommendResponseDTO.RecommendQuestionDTO> recommendQuestionDTOList);

    @GetMapping(value = "/")
    AIResponseDto test();
}
