package com.mtvs.sejong.playlog.service;

import com.mtvs.sejong.playlog.domain.Game;
import com.mtvs.sejong.playlog.domain.PlayLog;
import com.mtvs.sejong.playlog.dto.GameResponseDTO;
import com.mtvs.sejong.playlog.dto.PlayLogRequestDTO;
import com.mtvs.sejong.playlog.dto.PlayLogScoreDTO;
import com.mtvs.sejong.playlog.dto.PlayLogResponseDTO;
import com.mtvs.sejong.playlog.repository.GameRepository;
import com.mtvs.sejong.playlog.repository.PlayLogRepository;
import com.mtvs.sejong.user.domain.User;
import com.mtvs.sejong.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PlayLogService {

    private final PlayLogRepository playLogRepository;
    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    public PlayLogService(PlayLogRepository playLogRepository, GameRepository gameRepository, UserRepository userRepository) {
        this.playLogRepository = playLogRepository;
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
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

    @Transactional(readOnly = true)
    public List<GameResponseDTO.GameLogDTO> getPlayLog(Long currentUserId) {

        // 1. 현재 사용자의 게임을 최신순으로 조회
        List<Game> games = gameRepository.findAllByUserIdOrderByCreatedAtDesc(currentUserId);

        // 2. 각 게임에 대해 partnerId를 이용해 파트너의 닉네임을 조회
        return games.stream().map(game -> {
            String partnerNickname = userRepository.findById(game.getPartnerId())
                    .map(User::getNickname)
                    .orElse("Unknown Partner"); // 기본값 설정

            // 3. 게임의 gameId에 해당하는 플레이 로그 조회
            List<PlayLog> playLogs = playLogRepository.findByGameId(game.getId());
            List<PlayLogResponseDTO.PlayLogDTO> playLogDTOs = playLogs.stream().map(playLog -> {
                LocalDateTime createdAt = playLog.getCreatedAt();
                LocalDateTime updatedAt = playLog.getUpdatedDate();
                Duration duration = Duration.between(createdAt, updatedAt);

                // Duration 값을 읽기 쉬운 형식으로 변환
                String formattedDuration = formatDuration(duration);

                // DTO 생성
                return PlayLogResponseDTO.PlayLogDTO.builder()
                        .roomNumber(playLog.getRoomNumber())
                        .duration(formattedDuration)
                        .build();
            }).collect(Collectors.toList());

            // GameLogDTO 생성
            return GameResponseDTO.GameLogDTO.builder()
                    .gameId(game.getId())
                    .partnerNickname(partnerNickname)
                    .playLogs(playLogDTOs)
                    .build();
        }).collect(Collectors.toList()); // 최종 DTO 리스트 반환
    }

    // Duration 형식 변환 메서드
    private String formatDuration(Duration duration) {
        long seconds = duration.getSeconds();
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long remainingSeconds = seconds % 60;

        if (hours > 0) {
            return String.format("%d시간 %d분 %d초", hours, minutes, remainingSeconds);
        } else if (minutes > 0) {
            return String.format("%d분 %d초", minutes, remainingSeconds);
        } else {
            return String.format("%d초", remainingSeconds);
        }
    }

    // 평균 탈출 시간을 계산하는 로직
    public List<PlayLogScoreDTO> getUserAverageScores(Long userId) {
        List<Game> games = gameRepository.findAllByUserIdOrderByCreatedAtDesc(userId);

        // 방 번호 별 평균 시간 계산
        Map<Integer, Double> averageTimesByRoom = games.stream()
                .flatMap(game -> playLogRepository.findByGameId(game.getId()).stream())
                .collect(Collectors.groupingBy(
                        PlayLog::getRoomNumber,
                        Collectors.averagingDouble(log -> Duration.between(log.getCreatedAt(), log.getUpdatedDate()).getSeconds())
                ));

        // Map을 DTO로 변환
        return averageTimesByRoom.entrySet().stream()
                .map(entry -> new PlayLogScoreDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
