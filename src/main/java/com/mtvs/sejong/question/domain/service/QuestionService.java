package com.mtvs.sejong.question.domain.service;

import com.mtvs.sejong.question.application.dto.RecommendRequestDTO;
import com.mtvs.sejong.question.application.dto.RecommendResponseDTO;
import com.mtvs.sejong.question.domain.aggregate.Question;
import com.mtvs.sejong.question.domain.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionService {

    private final QuestionRepository questionRepository;

    // questionType으로 검색하여 추천 질문을 반환
    public List<RecommendRequestDTO.QuestionDTO> getRecommendedQuestions() {

        // user weak question type 확인
        String weakQuestionType = "띄어쓰기";

        // weak_question_type으로 질문 검색
        List<Question> questionList = questionRepository.findByQuestionType(weakQuestionType);

        // 검색된 Question 객체들을 RecommendRequestDTO.QuestionDTO로 변환
        return questionList.stream()
                .map(question -> new RecommendRequestDTO.QuestionDTO(
                        question.getQuestionId(),
                        question.getQuestionType(),
                        question.getQuestion(),
                        question.getAnswer(),
                        question.getDifficultyLevel(),
                        question.getCreatedAt().toString(), // LocalDateTime을 String으로 변환
                        question.getPopularityScore(),
                        question.getQuestionFormat()
                ))
                .collect(Collectors.toList());
    }
}
