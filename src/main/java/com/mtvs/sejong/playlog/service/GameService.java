package com.mtvs.sejong.playlog.service;

import com.mtvs.sejong.playlog.dto.GameRequestDTO;
import com.mtvs.sejong.playlog.dto.GameResponseDTO;
import com.mtvs.sejong.playlog.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameResponseDTO.createGameDTO createGame(Long currentUserId, GameRequestDTO.createGameDTO requestDTO) {

        // Game 생성

        // GameId 조회

        return null;
    }
}
