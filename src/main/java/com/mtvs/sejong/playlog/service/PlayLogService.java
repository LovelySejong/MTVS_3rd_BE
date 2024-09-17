package com.mtvs.sejong.playlog.service;

import com.mtvs.sejong.playlog.domain.PlayLog;
import com.mtvs.sejong.playlog.dto.PlayLogRequestDTO;
import com.mtvs.sejong.playlog.repository.PlayLogRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayLogService {

    private final PlayLogRepository playLogRepository;

    public PlayLogService(PlayLogRepository playLogRepository) {
        this.playLogRepository = playLogRepository;
    }

    public PlayLog savePlayLog(PlayLogRequestDTO playLogRequestDTO, Long getCurrentUserId) {
        PlayLog playLog = new PlayLog();
        playLog.setRoomNumber(playLogRequestDTO.getRoomNumber());
        playLog.setUserId(getCurrentUserId);

        System.out.println("playLog = " + playLog);

        return playLogRepository.save(playLog);
    }

    public void getPlayLog(Long currentUserId) {
        PlayLog playLog = playLogRepository.findPlayLogByRoomNumber(1);

        System.out.println("playLog = " + playLog);
        System.out.println("playLog = " + playLog.getCreatedAt());

        playLogRepository.save(playLog);

        PlayLog playLog1 = playLogRepository.findPlayLogByRoomNumber(1);

        System.out.println("playLog = " + playLog1.getUpdatedDate());
    }
}
