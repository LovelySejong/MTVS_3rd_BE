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
        private String duration;  // 포맷된 소요 시간 (예: "4초", "3분 20초")
    }

}
