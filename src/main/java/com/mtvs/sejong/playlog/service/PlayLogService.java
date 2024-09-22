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

    public void savePlayLog(PlayLogRequestDTO playLogRequestDTO, Long getCurrentUserId) {

        int roomNumber = playLogRequestDTO.getRoomNumber();

        PlayLog playLog = new PlayLog();
        playLog.setRoomNumber(roomNumber);
        playLog.setUserId(getCurrentUserId);

        System.out.println("playLog = " + playLog);

        // 이전 방 마무리
        if(roomNumber != 1) {
            updatePrevRoomPlayLog(roomNumber - 1);
        }

        playLogRepository.save(playLog);
    }

    private void updatePrevRoomPlayLog(int prevRoomNumber) {

        PlayLog prevPlayLog = playLogRepository.findPlayLogByRoomNumber(prevRoomNumber);
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
