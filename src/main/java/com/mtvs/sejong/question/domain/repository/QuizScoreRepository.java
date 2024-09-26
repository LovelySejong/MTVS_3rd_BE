package com.mtvs.sejong.question.domain.repository;

import com.mtvs.sejong.question.application.dto.QuizScoreDTO;
import com.mtvs.sejong.question.domain.aggregate.QuizScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuizScoreRepository extends JpaRepository<QuizScore, Long> {

    @Query("SELECT new com.mtvs.sejong.question.application.dto.QuizScoreDTO(q.questionType, AVG(q.score)) " +
            "FROM QuizScore q WHERE q.userId = :userId GROUP BY q.questionType")
    List<QuizScoreDTO> findAverageScoreByUserId(@Param("userId") Long userId);
}
