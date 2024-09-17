package com.mtvs.sejong.playlog.controller;

import com.mtvs.sejong.playlog.dto.PlayLogRequestDTO;
import com.mtvs.sejong.playlog.service.PlayLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        playLogService.savePlayLog(playLogRequestDTO, getCurrentUserId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/clear")
    public ResponseEntity<?> getPlayLog() {
        playLogService.getPlayLog(getCurrentUserId());
        return ResponseEntity.ok().build();
    }
}
