package com.mtvs.sejong.question.domain.repository;

import com.mtvs.sejong.question.domain.aggregate.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByQuestionType(String questionType);
}
