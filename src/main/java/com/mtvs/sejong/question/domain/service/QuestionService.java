package com.mtvs.sejong.question.domain.service;

import com.mtvs.sejong.question.application.dto.RecommendRequestDTO;
import com.mtvs.sejong.question.application.dto.RecommendResponseDTO;
import com.mtvs.sejong.question.domain.aggregate.Question;
import com.mtvs.sejong.question.domain.repository.QuestionRepository;
import com.mtvs.sejong.question.openfeign.QuestionFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionFeignClient questionFeignClient;

    // questionType으로 검색
    public List<RecommendResponseDTO.RecommendQuestionDTO> findQuestionByType() {

        // user weak question type 확인
        String weakQuestionType = "띄어쓰기";

        // weak_question_type으로 질문 검색 후 리스트에 저장
        List<Question> questionList = questionRepository.findByQuestionType(weakQuestionType);

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
                            question.getCreatedAt(),
                            question.getPopularityScore(),
                            question.getQuestionFormat()
                    );
            // list로 add
            recommendQuestionDTOList.add(recommendQuestionDTO);
        }

        // RecommendResponseDTO 생성하여 반환
//        return dto;
        return recommendQuestionDTOList;
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
