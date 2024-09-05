package com.mtvs.sejong.question.domain.service;

import com.mtvs.sejong.question.application.dto.CreateQuestionDTO;
import com.mtvs.sejong.question.domain.repository.QuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class QuestionTests {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionRepository questionRepository;

    // c만
//    @DisplayName("문제 생성 테스트")
//    @Test
//    void questionCreateTest() {
//        CreateQuestionDTO dto = new CreateQuestionDTO(
//                1L,
//                "띄어쓰기",
//                "띄어쓰기 올바르게 고치시오",
//                "hi",
//                "초급",
//                80,
//                "객관식"
//        );
//        CreateQuestionDTO savedQuestDTO = questionService.createQuestion();
//
//        assertNotNull(savedQuestDTO);
//        assertNotNull(savedQuestDTO.getQuestionId());
//
//    }

}
