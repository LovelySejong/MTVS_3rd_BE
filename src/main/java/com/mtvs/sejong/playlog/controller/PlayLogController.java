package com.mtvs.sejong.playlog.controller;

import com.mtvs.sejong._core.utils.ApiUtils;
import com.mtvs.sejong.playlog.dto.GameResponseDTO;
import com.mtvs.sejong.playlog.dto.PlayLogRequestDTO;
import com.mtvs.sejong.playlog.dto.PlayLogScoreDTO;
import com.mtvs.sejong.playlog.service.GameService;
import com.mtvs.sejong.playlog.service.PlayLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mtvs.sejong._core.utils.SecurityUtils.getCurrentUserId;

@RestController
@RequestMapping("/api/log")
public class PlayLogController {
    private final PlayLogService playLogService;
    private final GameService gameService;

    public PlayLogController(PlayLogService playLogService, GameService gameService) {
        this.playLogService = playLogService;
        this.gameService = gameService;
    }

    @PostMapping("/clear")
    public ResponseEntity<?> savePlayLog(@RequestBody PlayLogRequestDTO playLogRequestDTO) {

        System.out.println("playLogRequestDTO = " + playLogRequestDTO);

        playLogService.savePlayLog(playLogRequestDTO, getCurrentUserId());
        return ResponseEntity.ok().body(ApiUtils.success(null));
    }

    @GetMapping
    public ResponseEntity<?> getPlayLog() {

        List<GameResponseDTO.GameLogDTO> gameLogs = playLogService.getPlayLog(getCurrentUserId());

        return ResponseEntity.ok().body(ApiUtils.success(gameLogs));
    }

    @GetMapping("/avgscores")
    public ResponseEntity<List<PlayLogScoreDTO>> getUserAvgScores() {
        Long userId = getCurrentUserId();
//        List<GameResponseDTO.GameLogDTO> gameList = playLogService.getPlayLog(userId);
        List<PlayLogScoreDTO> averageScores = playLogService.getUserAverageScores(userId);
        System.out.println("avg log"+averageScores);
        return ResponseEntity.ok(averageScores);
    }
}
