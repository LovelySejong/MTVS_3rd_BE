package com.mtvs.sejong.playlog.dto;

public class GameRequestDTO {

    public record createGameDTO(
            // partner email
            String partnerID
    ) {}
}
