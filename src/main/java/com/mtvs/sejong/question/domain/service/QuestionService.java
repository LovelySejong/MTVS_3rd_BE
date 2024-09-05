package com.mtvs.sejong.question.domain.service;

import com.mtvs.sejong.question.application.dto.CreateQuestionDTO;
import com.mtvs.sejong.question.domain.aggregate.Question;
import com.mtvs.sejong.question.domain.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QuestionService {

    private QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public CreateQuestionDTO createQuestion(CreateQuestionDTO questionDTO) {

        Question question = questionDTO.toEntity();
        Question savedQuestion = questionRepository.save(question);

        return CreateQuestionDTO.toQuestionRequestDTO(savedQuestion);
    }
}
