package com.mtvs.sejong.playlog.repository;

import com.mtvs.sejong.playlog.domain.PlayLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayLogRepository extends JpaRepository <PlayLog, Long> {
}
