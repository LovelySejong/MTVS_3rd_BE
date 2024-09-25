package com.mtvs.sejong.playlog.controller;

import com.mtvs.sejong._core.utils.ApiUtils;
import com.mtvs.sejong.playlog.dto.GameRequestDTO;
import com.mtvs.sejong.playlog.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/game")
public class GameController {

    private final GameService gameService;

    @PostMapping
    public ResponseEntity<?> enterGame(@RequestBody GameRequestDTO.createGameDTO requestDTO) {

        gameService.createGame(requestDTO);

        return ResponseEntity.ok().body(ApiUtils.success(null));
    }
}
