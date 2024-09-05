package com.mtvs.sejong.question.domain.service;

import com.mtvs.sejong.question.application.dto.CreateQuestionDTO;
import com.mtvs.sejong.question.domain.aggregate.Question;
import com.mtvs.sejong.question.domain.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionService {

    private final QuestionRepository questionRepository;

    // 요청할 URL 주소
//    String url =
//    public void createQuestion() {
//
//        // QuestionDTO를 List 로 응답을 받고
//
//        // QuestionDTOList => Question
//        List<Question> questions = new ArrayList<>();
//        questions.addAll
//
//        // questionRepository 에 저장
//
//    }
}
