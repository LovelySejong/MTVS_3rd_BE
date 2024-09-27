package com.mtvs.sejong.playlog.repository;

import com.mtvs.sejong.playlog.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findAllByUserIdOrderByCreatedAtDesc(Long userId);

}
