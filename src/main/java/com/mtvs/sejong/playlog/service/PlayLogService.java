package com.mtvs.sejong.playlog.service;

import com.mtvs.sejong.playlog.domain.PlayLog;
import com.mtvs.sejong.playlog.dto.PlayLogRequestDTO;
import com.mtvs.sejong.playlog.repository.PlayLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PlayLogService {

    private final PlayLogRepository playLogRepository;

    public PlayLogService(PlayLogRepository playLogRepository) {
        this.playLogRepository = playLogRepository;
    }

    @Transactional
    public void savePlayLog(PlayLogRequestDTO playLogRequestDTO, Long getCurrentUserId) {

        int roomNumber = playLogRequestDTO.getRoomNumber();
        long gameId = playLogRequestDTO.getGameId();

        System.out.println("roomNumber = " + roomNumber);

        PlayLog playLog = new PlayLog();
        playLog.setRoomNumber(roomNumber);
        playLog.setGameId(gameId);

        System.out.println("playLog = " + playLog);

        updatePrevRoomPlayLog(roomNumber - 1, gameId);

        playLogRepository.save(playLog);
    }

    private void updatePrevRoomPlayLog(int prevRoomNumber, long gameId) {

        playLogRepository.findPlayLogByGameIdAndRoomNumber(gameId, prevRoomNumber)
                .ifPresent(prevPlayLog -> prevPlayLog.setUpdatedDate(LocalDateTime.now()));
    }

    public void getPlayLog(Long currentUserId) {

    }
}
