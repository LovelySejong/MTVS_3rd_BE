package com.mtvs.sejong.question.domain.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QuestionTests {

    @Autowired
    private QuestionService questionService;

    // cr만
    @DisplayName("문제 생성 테스트")
    @Test
    void questionCreateTest() {

    }

}
