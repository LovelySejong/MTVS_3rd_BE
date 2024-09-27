package com.mtvs.sejong.playlog.repository;

import com.mtvs.sejong.playlog.domain.PlayLog;
import com.mtvs.sejong.playlog.dto.PlayLogScoreDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayLogRepository extends JpaRepository <PlayLog, Long> {

    @Query("select pl from PlayLog pl where pl.gameId = :gameId and pl.roomNumber = :roomNumber")
    Optional<PlayLog> findPlayLogByGameIdAndRoomNumber(@Param("gameId") long gameId, @Param("roomNumber") int roomNumber);

    List<PlayLog> findByGameId(Long gameId);
    PlayLog findPlayLogByRoomNumber(int roomNumber);

    @Query(value = "SELECT p.room_number AS roomNumber, AVG(TIMESTAMPDIFF(SECOND, p.created_at, p.updated_date)) AS averageTime " +
            "FROM play_log p WHERE p.user_id = :userId GROUP BY p.room_number", nativeQuery = true)
    List<Object[]> findUserAveragePlayLog(@Param("userId") Long userId);
}
