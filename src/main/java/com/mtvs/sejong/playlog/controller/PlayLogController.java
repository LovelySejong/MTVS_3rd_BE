package com.mtvs.sejong.playlog.controller;

import com.mtvs.sejong._core.utils.ApiUtils;
import com.mtvs.sejong.playlog.dto.GameResponseDTO;
import com.mtvs.sejong.playlog.dto.PlayLogRequestDTO;
import com.mtvs.sejong.playlog.dto.PlayLogScoreDTO;
import com.mtvs.sejong.playlog.service.PlayLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mtvs.sejong._core.utils.SecurityUtils.getCurrentUserId;

@RestController
@RequestMapping("/api/log")
public class PlayLogController {
    private final PlayLogService playLogService;

    public PlayLogController(PlayLogService playLogService) {
        this.playLogService = playLogService;
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
        List<PlayLogScoreDTO> averageScores = playLogService.getUserAverageScores(userId);
        System.out.println(averageScores);
        return ResponseEntity.ok(averageScores);
    }
}
