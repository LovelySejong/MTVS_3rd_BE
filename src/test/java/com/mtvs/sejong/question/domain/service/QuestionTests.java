package com.mtvs.sejong.question.domain.service;

import com.mtvs.sejong.question.application.dto.RecommendRequestDTO;
import com.mtvs.sejong.question.domain.repository.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class QuestionTests {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionRepository questionRepository;

//    private static Stream<Arguments> newQuestion() {
//        return Stream.of(
//                Arguments.of(new RecommendRequestDTO.QuestionDTO(1L, "띄어쓰기", "다음 문장에서 띄어쓰기를 올바르게 고치세요. 1)서울 시립 미술관 2) 서울시립 미술관", "1", "중급", 85L, "객관식")),
//                Arguments.of(new RecommendRequestDTO.QuestionDTO(2L, "한자어", "다음 중 한자어로 된 단어를 고르세요. 1)서울 2)학생", "2", "초급", 95L, "객관식")),
//                Arguments.of(new RecommendRequestDTO.QuestionDTO(3L, "띄어쓰기", "다음 문장에서 띄어쓰기를 올바르게 고치세요. 1)서울 시립 미술관 2) 서울시립 미술관", "1", "중급", 85L, "객관식"))
//        );
//    }
//
//    @DisplayName("문제 생성 테스트")
//    @ParameterizedTest
//    @MethodSource("newQuestion")
//    @Transactional
//    void testRegistQuestion(RecommendRequestDTO.QuestionDTO questionDTO) {
//
//        Assertions.assertDoesNotThrow(
//                () -> questionService.registNewQuestion(questionDTO)
//        );
//
//        questionService.findAllQuestions().forEach(System.out::println);
//    }

//    @DisplayName("특정 문제 유형으로 검색")
//    @ParameterizedTest
//    @ValueSource(strings = {"띄어쓰기", "한자어"})
//    void testFindQuestionByType(String questionType) {
//        // 검색 시 예외가 발생하지 않도록 확인
//        Assertions.assertDoesNotThrow(() -> {
//            // 특정 문제 유형으로 문제 검색
//            RecommendResponseDTO responseDTO = questionService.findQuestionByType(questionType);
//
//            // 검색된 문제가 null이 아닌지 확인
//            assertNotNull(responseDTO);
//            assertNotNull(responseDTO.getProblems());
//
//            // 검색된 문제가 있는지 확인 (최소 한 문제 이상 포함되는지)
//            List<RecommendResponseDTO.RecommendQuestionDTO> questionList = responseDTO.getProblems();
//            Assertions.assertTrue(questionList.size() > 0, "문제가 최소 1개 이상 있어야 합니다.");
//
//            // 문제 유형이 기대한 값과 일치하는지 확인
//            questionList.forEach(question -> {
//                Assertions.assertEquals(questionType, question.getQuestionType(), "문제 유형이 일치하지 않습니다.");
//            });
//        });
//    }

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
