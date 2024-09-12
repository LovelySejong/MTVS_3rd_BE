package com.mtvs.sejong.playlog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayLogRequestDTO {

    private String RoomName;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
}
