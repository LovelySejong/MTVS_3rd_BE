package com.mtvs.sejong.question.domain.service;

import com.mtvs.sejong.question.application.dto.RecommendRequestDTO;
import com.mtvs.sejong.question.application.dto.RecommendResponseDTO;
import com.mtvs.sejong.question.domain.aggregate.Question;
import com.mtvs.sejong.question.domain.repository.QuestionRepository;
import com.mtvs.sejong.question.openfeign.QuestionFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionService {

    private final QuestionRepository questionRepository;
    private QuestionFeignClient questionFeignClient;

    // questionType으로 검색
    public List<RecommendResponseDTO.RecommendQuestionDTO> findQuestionByType(RecommendRequestDTO.QuestionDTO questionDTO) {

        // user weak question type 확인
//        String weakQuestionType = "띄어쓰기";

        // weak_question_type으로 질문 리스트 검색
        List<Question> questionList = questionRepository.findByQuestionType(questionDTO.getQuestionType());

        // 검색된 Question 객체들을 RecommendQuestionDTO로 변환
        List<RecommendResponseDTO.RecommendQuestionDTO> recommendQuestionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            RecommendResponseDTO.RecommendQuestionDTO recommendQuestionDTO =
                    new RecommendResponseDTO.RecommendQuestionDTO(
                            question.getQuestionId(),
                            question.getQuestionType(),
                            question.getQuestion(),
                            question.getAnswer(),
                            question.getDifficultyLevel(),
                            question.getPopularityScore(),
                            question.getQuestionFormat()
                    );
            recommendQuestionDTOList.add(recommendQuestionDTO);
        }

        return recommendQuestionDTOList;
        // RecommendResponseDTO 생성하여 반환
//        return dto;
    }
}

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
