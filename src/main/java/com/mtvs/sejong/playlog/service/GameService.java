package com.mtvs.sejong.playlog.service;

import com.mtvs.sejong.playlog.dto.GameRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class GameService {

    public void createGame(GameRequestDTO.createGameDTO requestDTO) {

    }
}
