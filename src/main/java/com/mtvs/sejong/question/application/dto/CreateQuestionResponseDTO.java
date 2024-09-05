package com.mtvs.sejong.question.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CreateQuestionResponseDTO {

    List<CreateQuestionDTO> problems;
    
    // 문제 생성 요청을 AI에게 보내고 응답을 CreateQuestionResponseDTO 로 받고
    // 그 problems, QuestionDTO List 요소 하나하나를 Question 엔티티로 변환 후 repository 에 저장 ===> 문제 생성

//    for(QuestionDTO questionDTO : problems) {
//
//    }
}
