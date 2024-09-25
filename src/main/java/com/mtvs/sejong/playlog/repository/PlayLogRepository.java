package com.mtvs.sejong.playlog.repository;

import com.mtvs.sejong.playlog.domain.PlayLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayLogRepository extends JpaRepository <PlayLog, Long> {

    PlayLog findPlayLogByRoomNumber(int roomNumber);
}
