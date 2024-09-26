package com.mtvs.sejong.playlog.repository;

import com.mtvs.sejong.playlog.domain.PlayLog;
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
}
