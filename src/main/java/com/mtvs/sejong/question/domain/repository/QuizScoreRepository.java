package com.mtvs.sejong.question.domain.repository;

import com.mtvs.sejong.question.domain.aggregate.QuizScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizScoreRepository extends JpaRepository<QuizScore, Long> {
}
