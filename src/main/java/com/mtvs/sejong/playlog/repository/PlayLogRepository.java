package com.mtvs.sejong.playlog.repository;

import com.mtvs.sejong.playlog.domain.PlayLog;
import com.mtvs.sejong.playlog.dto.PlayLogScoreDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayLogRepository extends JpaRepository <PlayLog, Long> {

    PlayLog findPlayLogByRoomNumber(int roomNumber);

    @Query(value = "SELECT p.room_number AS roomNumber, AVG(TIMESTAMPDIFF(SECOND, p.created_at, p.updated_date)) AS averageTime " +
            "FROM play_log p WHERE p.user_id = :userId GROUP BY p.room_number", nativeQuery = true)
    List<PlayLogScoreDTO> findUserAveragePlayLog(@Param("userId") Long userId);
}
