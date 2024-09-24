package com.mtvs.sejong.playlog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayLogRequestDTO {

    @JsonProperty("RoomNumber") // JSON에서 대문자를 사용하여 역직렬화 문제 해결
    private int roomNumber;
}
