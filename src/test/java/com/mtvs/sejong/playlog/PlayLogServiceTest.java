package com.mtvs.sejong.playlog;

import com.mtvs.sejong.playlog.dto.PlayLogRequestDTO;
import com.mtvs.sejong.playlog.service.PlayLogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PlayLogServiceTest {

    @Autowired
    private PlayLogService playLogService;

    private PlayLogRequestDTO playLog1;
    private PlayLogRequestDTO playLog2;

    @BeforeEach
    public void setUp() {

        playLog1 = new PlayLogRequestDTO();
        playLog1.setRoomName("Quiz Room 1");
        playLog1.setEntryTime(LocalDateTime.of(2024, 9, 13, 10, 0));
        playLog1.setExitTime(LocalDateTime.of(2024, 9, 13, 10, 30));
        System.out.println(playLog1);

        playLog2 = new PlayLogRequestDTO();
        playLog2.setRoomName("Quiz Room 2");
        playLog2.setEntryTime(LocalDateTime.of(2024, 9, 13, 10, 35));
        playLog2.setExitTime(LocalDateTime.of(2024, 9, 13, 11, 0));
    }

    @Test
    public void testCreatePlayLog() {
        playLogService.savePlayLog(playLog1, 1L);
        playLogService.savePlayLog(playLog2, 2L);

        assertEquals("Quiz Room 1", playLog1.getRoomName());
        assertEquals("Quiz Room 2", playLog2.getRoomName());
    }
}
