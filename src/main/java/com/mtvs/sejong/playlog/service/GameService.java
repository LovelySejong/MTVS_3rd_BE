package com.mtvs.sejong.playlog.service;

import com.mtvs.sejong._core.error.exception.Exception400;
import com.mtvs.sejong._core.error.exception.Exception403;
import com.mtvs.sejong.playlog.domain.Game;
import com.mtvs.sejong.playlog.dto.GameRequestDTO;
import com.mtvs.sejong.playlog.dto.GameResponseDTO;
import com.mtvs.sejong.playlog.repository.GameRepository;
import com.mtvs.sejong.user.domain.User;
import com.mtvs.sejong.user.repository.UserRepository;
import com.mtvs.sejong.user.service.UserService;
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
    private final UserRepository userRepository;

    @Transactional
    public GameResponseDTO.createGameDTO createGame(Long currentUserId, GameRequestDTO.createGameDTO requestDTO) {

        if (currentUserId == null || currentUserId <= 0) {
            throw new Exception403("인증된 사용자만 게임을 생성할 수 있습니다.");
        }

        User user = userRepository.findByEmail(requestDTO.partnerID())
                .orElseThrow(() -> new Exception400("해당 이메일의 회원이 존재하지 않습니다."));

        if(currentUserId.equals(user.getId())) {
            throw new Exception400("중복 게임 생성을 막겠습니다.");
        }

        // Game 생성
        Game newGame = Game.builder()
                .userId(currentUserId)
                .partnerId(user.getId())
                .build();

        System.out.println("newGame = " + newGame);

        Game savedGame = gameRepository.save(newGame);

        return new GameResponseDTO.createGameDTO(savedGame.getId().intValue());
    }
}
