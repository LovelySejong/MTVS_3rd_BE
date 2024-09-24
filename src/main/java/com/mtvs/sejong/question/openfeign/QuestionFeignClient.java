package com.mtvs.sejong.question.openfeign;

import com.mtvs.sejong.question.application.dto.ChatRequestDTO;
import com.mtvs.sejong.question.application.dto.ChatResponseDTO;
import com.mtvs.sejong.question.application.dto.RecommendRequestDTO;
import com.mtvs.sejong.question.application.dto.RecommendResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "question-service", url = "http://metaai.iptime.org:7880")
public interface QuestionFeignClient {

    @PostMapping("/recommend")
    RecommendResponseDTO sendQuestions(@RequestBody RecommendRequestDTO recommendRequestDTO);

    @PostMapping(value = "/chatbot")
    ChatResponseDTO chat(ChatRequestDTO chatRequestDTO);
}
