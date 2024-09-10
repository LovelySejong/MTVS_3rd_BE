package com.mtvs.sejong.question.openfeign;

import com.mtvs.sejong.question.application.dto.RecommendRequestDTO;
import com.mtvs.sejong.question.application.dto.RecommendResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "question-service", url = "http://192.168.0.64:7880")
public interface QuestionFeignClient {

    @PostMapping("/recommend")
    RecommendResponseDTO sendQuestions(@RequestBody RecommendRequestDTO recommendRequestDTO);

    // 필요시 다른 메서드 추가
    @GetMapping(value = "/")
    AIResponseDto test();
}
