package com.mtvs.sejong.playlog;

import com.mtvs.sejong.playlog.dto.PlayLogRequestDTO;
import com.mtvs.sejong.playlog.service.PlayLogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PlayLogServiceTest {

    @Autowired
    private PlayLogService playLogService;

    private PlayLogRequestDTO playLog1;
    private PlayLogRequestDTO playLog2;

    @BeforeEach
    public void setUp() {

        playLog1 = new PlayLogRequestDTO(1);
        System.out.println(playLog1);

        playLog2 = new PlayLogRequestDTO(2);
        System.out.println(playLog2);
    }

    @Test
    public void testCreatePlayLog() {
        playLogService.savePlayLog(playLog1, 1L);
        playLogService.savePlayLog(playLog2, 2L);

        assertEquals(1, playLog1.RoomNumber());
        assertEquals(2, playLog2.RoomNumber());
    }
}
