package com.mtvs.sejong.question.application.controller;

import com.mtvs.sejong._core.utils.ApiUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

    @GetMapping
    public ResponseEntity<?> createQuestion() {

        return ResponseEntity.ok().body(ApiUtils.success(null));
    }
}
