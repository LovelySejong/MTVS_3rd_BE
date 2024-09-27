package com.mtvs.sejong.playlog.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

public class GameResponseDTO {

    public record createGameDTO(
            int gameId
    ) {}

    @Data
    @Builder
    public static class GameLogDTO {
        private long gameId;
        private String partnerNickname;
        private List<PlayLogResponseDTO.PlayLogDTO> playLogs;
    }
}
