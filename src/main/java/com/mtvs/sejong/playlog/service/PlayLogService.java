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
        playLog.setRoomName(playLogRequestDTO.getRoomName());
        playLog.setEntryTime(playLogRequestDTO.getEntryTime());
        playLog.setExitTime(playLogRequestDTO.getExitTime());
        playLog.setUserId(getCurrentUserId);

        return playLogRepository.save(playLog);
    }
}
