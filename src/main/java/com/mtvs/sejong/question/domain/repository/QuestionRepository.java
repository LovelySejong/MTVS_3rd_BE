package com.mtvs.sejong.question.domain.repository;

import com.mtvs.sejong.question.domain.aggregate.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}
