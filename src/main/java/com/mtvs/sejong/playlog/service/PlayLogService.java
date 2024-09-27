package com.mtvs.sejong.playlog.service;

import com.mtvs.sejong.playlog.domain.PlayLog;
import com.mtvs.sejong.playlog.dto.PlayLogRequestDTO;
import com.mtvs.sejong.playlog.dto.PlayLogScoreDTO;
import com.mtvs.sejong.playlog.repository.PlayLogRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PlayLogService {

    private final PlayLogRepository playLogRepository;

    public PlayLogService(PlayLogRepository playLogRepository) {
        this.playLogRepository = playLogRepository;
    }

    public void savePlayLog(PlayLogRequestDTO playLogRequestDTO, Long getCurrentUserId) {

        int roomNumber = playLogRequestDTO.getRoomNumber();

        System.out.println("roomNumber = " + roomNumber);

        PlayLog playLog = new PlayLog();
        playLog.setRoomNumber(roomNumber);
        playLog.setUserId(getCurrentUserId);

        System.out.println("playLog = " + playLog);

        updatePrevRoomPlayLog(roomNumber - 1);

        playLogRepository.save(playLog);
    }

    private void updatePrevRoomPlayLog(int prevRoomNumber) {

        PlayLog prevPlayLog = playLogRepository.findPlayLogByRoomNumber(prevRoomNumber);

        if(prevPlayLog != null) {
            prevPlayLog.setUpdatedDate(LocalDateTime.now());
        }
    }

    public void getPlayLog(Long currentUserId) {

    }

    public List<PlayLogScoreDTO> getUserAverageScores(Long userId) {
        return playLogRepository.findUserAveragePlayLog(userId);
    }
}
