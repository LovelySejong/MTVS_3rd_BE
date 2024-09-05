package com.mtvs.sejong.question.application.controller;

import com.mtvs.sejong._core.utils.ApiUtils;
import com.mtvs.sejong.question.domain.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/question")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping
    public ResponseEntity<?> createQuestion() {

        questionService.createQuestion();

        return ResponseEntity.ok().body(ApiUtils.success(null));
    }
}
