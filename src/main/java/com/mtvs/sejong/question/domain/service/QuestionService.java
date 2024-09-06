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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionService {

    private final QuestionRepository questionRepository;
    private QuestionFeignClient questionFeignClient;

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

//    public RecommendResponseDTO findQuestionByType(String questionType) {
//        // 특정 문제 유형에 해당하는 문제들을 데이터베이스에서 검색
//        List<Question> questionList = questionRepository.findByQuestionType(questionType);
//
//        // 검색된 Question 객체들을 RecommendResponseDTO.RecommendQuestionDTO로 변환
//        List<RecommendResponseDTO.RecommendQuestionDTO> recommendQuestionDTOList = questionList.stream()
//                .map(question -> new RecommendResponseDTO.RecommendQuestionDTO(
//                        question.getQuestionId(),
//                        question.getQuestionType(),
//                        question.getQuestion(),
//                        question.getAnswer(),
//                        question.getDifficultyLevel(),
//                        question.getPopularityScore(),
//                        question.getQuestionFormat()
//                ))
//                .collect(Collectors.toList());
//
//        // RecommendResponseDTO를 생성하고 반환
//        return new RecommendResponseDTO(recommendQuestionDTOList);
//    }



    // 문제 생성 테스트
    public void registNewQuestion(RecommendRequestDTO.QuestionDTO questionDTO) {

        Question newQuestion = new Question(
                questionDTO.getQuestionType(),
                questionDTO.getQuestion(),
                questionDTO.getAnswer(),
                questionDTO.getDifficultyLevel(),
                questionDTO.getPopularityScore(),
                questionDTO.getQuestionFormat()
        );

        questionRepository.save(newQuestion);
    }

    // 모든 문제 조회
    public List<RecommendRequestDTO.QuestionDTO> findAllQuestions() {

        return questionRepository.findAll()
                .stream()
                .map(question -> new RecommendRequestDTO.QuestionDTO(
                        question.getQuestionId(),
                        question.getQuestionType(),
                        question.getQuestion(),
                        question.getAnswer(),
                        question.getDifficultyLevel(),
                        question.getPopularityScore(),
                        question.getQuestionFormat()
                ))
                .collect(Collectors.toList());

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
