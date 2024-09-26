package com.mtvs.sejong.playlog.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;

public class PlayLogResponseDTO {

    @Data
    @Builder
    public static class PlayLogDTO {
        private int roomNumber;
        private LocalDateTime startedAt; // created_at
        private LocalDateTime endedAt; // updated_date
        private Duration duration;  // 소요 시간
    }

}
